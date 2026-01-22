package knightwood.kv.aop.floorcore.ksp.writer

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import knightwood.kv.aop.floorcore.ksp.processor.getArgumentValue
import org.slf4j.Logger
import java.util.ServiceLoader
import kotlin.also

object IDaoGeneratorFactory {
    private val cache = mutableMapOf<String, IDaoGenerator>()

    /**
     * 获取一个可以处理某种数据库类型，生成对应dao文件的IDaoGenerator类实例
     *
     * @param dbtype 数据库类型
     */
    fun create(dbtype: String?,kspLogger: KSPLogger, codeGenerator: CodeGenerator): IDaoGenerator? {
        if (dbtype.isNullOrBlank()) {
            return null
        }
        if (cache.containsKey(dbtype)) {
            return cache[dbtype]?.also {
                it.setArg(  kspLogger, codeGenerator)
            }
        }
        return ServiceLoader.load(IDaoGenerator::class.java).find {
            it.isSupport(dbtype)
        }?.also {
            cache[dbtype] = it
            it.setArg(  kspLogger, codeGenerator)
        }
    }
}

/**
 * 统一mmkv、datastore、preference生成dao文件接口
 * 具体功能实现由其他module提供
 */
interface IDaoGenerator {
    fun isSupport(dbtype: String): Boolean

    fun setArg(
        kspLogger: KSPLogger,
        codeGenerator: CodeGenerator,
    )

    /**
     * 根据@KVStore标记的类，生成dao类
     */
    fun generateFile(classDeclaration: KSClassDeclaration) {
        val kvStoreAnnotation = classDeclaration.annotations
            .firstOrNull {
                it.annotationType.resolve().declaration.qualifiedName?.asString() == "knightwood.kv.aop.floorcore.annotation.KVStore"
            }
        if (kvStoreAnnotation == null) {
            error("${classDeclaration.qualifiedName?.getQualifier()} should have @KVStore annotation")
        }
        // 5. 获取注解参数值
        val dbFileName = kvStoreAnnotation.getArgumentValue("name")
        val daoName = kvStoreAnnotation.getArgumentValue("daoName")
        val dbType = kvStoreAnnotation.getArgumentValue("type")

    }

    /**
     * 根据@KVField标记的属性，填充dao类
     */
    fun fillFile(ksPropertyDeclaration: List<KSPropertyDeclaration>) {}
}
