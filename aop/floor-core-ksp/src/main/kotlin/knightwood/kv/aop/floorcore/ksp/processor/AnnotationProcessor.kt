package knightwood.kv.aop.floorcore.ksp.processor

import com.github.knightwood.slf4j.kotlin.logFor
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.validate
import knightwood.kv.aop.floorcore.annotation.KVStore
import knightwood.kv.aop.floorcore.ksp.writer.IDaoGeneratorFactory

/**
 *
 * 处理注解、类、字段等通用信息。
 *
 * 具体的生成文件功能由其他module提供。
 *
 */
class AnnotationProcessor(val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    val kspLogger get() = environment.logger
    val logger = logFor("注解处理器")
    val codeGenerator get() = environment.codeGenerator

    override fun process(resolver: Resolver): List<KSAnnotated> {
        //所有标注了KVStore注解的符号信息
        val symbols = resolver
            .getSymbolsWithAnnotation(KVStore::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .toList()
        val ret = mutableListOf<KSAnnotated>()

        symbols.forEach { classDeclaration ->
            // 1. 检查是否合法
            if (!classDeclaration.validate()) {
                ret.add(classDeclaration)
                return@forEach
            }

            // 3. 检查是否是数据类
            if (!classDeclaration.isDataClass()) {
                kspLogger.error("@KVStore can only be applied to data classes", classDeclaration)
                ret.add(classDeclaration)
                return@forEach
            }

            // 4. 获取类上的 @KVStore 注解
            val kvStoreAnnotation = classDeclaration.annotations
                .firstOrNull {
                    it.annotationType.resolve().declaration.qualifiedName?.asString() == "knightwood.kv.aop.floorcore.annotation.KVStore"
                }
            if (kvStoreAnnotation == null) {
                kspLogger.error("Class should have @KVStore annotation", classDeclaration)
                ret.add(classDeclaration)
                return@forEach
            }

            // 5. 获取注解参数值
            val dbType = kvStoreAnnotation.getArgumentValue("type")
            // 6. 处理这个类
            val generator = IDaoGeneratorFactory.create(
                dbtype = dbType,
                codeGenerator = codeGenerator,
                kspLogger = kspLogger
            )
            generator?.let { generator ->
                generator.generateFile(classDeclaration)
                val fields = classDeclaration.getAllProperties().filter { declaration ->
                    declaration.modifiers.contains(Modifier.PUBLIC)
                }.toList()
                generator.fillFile(fields)
            }
        }
        return ret
    }
}

// 判断是否是数据类
internal fun KSClassDeclaration.isDataClass(): Boolean {
    return this.modifiers.contains(Modifier.DATA)
}

// 获取包名
internal val KSClassDeclaration.packageName: String
    get() = this.containingFile?.packageName?.asString() ?: ""

/**
 * 获取注解参数值
 * @param name 参数名
 * 例如
 * @KVStore(name = "user.pb", daoName = "UserDao")
 * name参数的值为"user.pb"
 * daoName参数的值为"UserDao"
 */
internal fun KSAnnotation.getArgumentValue(name: String): String? {
    return arguments.find { argument -> argument.name?.asString() == name }?.value as? String
}
