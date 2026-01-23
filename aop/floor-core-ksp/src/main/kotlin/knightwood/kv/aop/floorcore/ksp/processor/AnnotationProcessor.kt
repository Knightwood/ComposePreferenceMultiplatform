package knightwood.kv.aop.floorcore.ksp.processor

import com.github.knightwood.slf4j.kotlin.logFor
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ksp.toClassName
import knightwood.kv.aop.floorcore.annotation.KVStore
import knightwood.kv.aop.floorcore.ksp.writer.IDaoGeneratorFactory
import knightwood.kv.aop.floorcore.ksp.writer.KVAnnotationUtils
import knightwood.kv.aop.floorcore.ksp.writer.TypeConvertMapper

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
        //处理TypeConvertor
        TypeConvertMapper.process(resolver)

        //处理KVStore
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

            //不需要知道是否是数据类，只要有一个包括了全部字段的构造函数就行
//            // 3. 检查是否是数据类
//            if (!classDeclaration.isDataClass()) {
//                kspLogger.error("@KVStore can only be applied to data classes", classDeclaration)
//                ret.add(classDeclaration)
//                return@forEach
//            }

            // 4. 获取类上的 @KVStore 注解
            val kvStoreAnnotation = classDeclaration.annotations
                .firstOrNull {
                    it.shortName.asString() == KVStore::class.simpleName
                }
            if (kvStoreAnnotation == null) {
                kspLogger.error("Class should have @KVStore annotation", classDeclaration)
                ret.add(classDeclaration)
                return@forEach
            }

            // 5. 获取注解参数值
            val dbType = kvStoreAnnotation.getArgumentValue("type")
            val dbFileName = kvStoreAnnotation.getArgumentValue("name")
            val daoName = KVAnnotationUtils.parseDaoName(kvStoreAnnotation, classDeclaration)
            logger.info("dbFileName: $dbFileName, daoName: $daoName")

            // 6. 获取文件生成器
            val generator = IDaoGeneratorFactory.create(
                dbtype = dbType,
                codeGenerator = codeGenerator,
                kspLogger = kspLogger
            ) ?: error("No IDaoGenerator found for dbtype $dbType")

            // 7. 生成文件
            generator.generateFile(
                //输出类的包名直接和添加了@KVStore注解的类一致位置
                classDeclaration.packageName.asString(),
                dbFileName = dbFileName,
                daoName = daoName,
                classDeclaration = classDeclaration
            )
        }
        return ret
    }
}
