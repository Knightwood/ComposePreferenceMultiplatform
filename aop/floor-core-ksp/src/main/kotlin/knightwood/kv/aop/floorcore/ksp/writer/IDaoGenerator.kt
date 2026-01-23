package knightwood.kv.aop.floorcore.ksp.writer

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import org.slf4j.LoggerFactory
import java.util.ServiceLoader
import kotlin.also

private val logger = LoggerFactory.getLogger(IDaoGeneratorFactory::class.java)

object IDaoGeneratorFactory {
    private val cache = mutableMapOf<String, IDaoGenerator>()

    /**
     * 获取一个可以处理某种数据库类型，生成对应dao文件的IDaoGenerator类实例
     *
     * @param dbtype 数据库类型
     */
    fun create(dbtype: String?, kspLogger: KSPLogger, codeGenerator: CodeGenerator): IDaoGenerator? {
        if (dbtype.isNullOrBlank()) {
            return null
        }
        if (cache.containsKey(dbtype)) {
            return cache[dbtype]?.also {
                it.setArg(kspLogger, codeGenerator)
            }
        }
        var clas = ServiceLoader.load(IDaoGenerator::class.java)
        if (clas.count() < 1) {
            clas = ServiceLoader.load(
                IDaoGenerator::class.java,
                IDaoGenerator::class.java.classLoader
            )
        }
        if (clas.count() < 1) {
            error("No IDaoGenerator found for dbtype $dbtype")
        }
        return clas.find { it.isSupport(dbtype) }?.also {
            cache[dbtype] = it
            it.setArg(kspLogger, codeGenerator)
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
     * @param dbFileName 数据库文件名
     * @param daoName 要生成的dao类名
     * @param classDeclaration 要生成的dao类对应的实体类
     */
    fun generateFile(
        outPackage:String,
        dbFileName: String?,
        daoName: String,
        classDeclaration: KSClassDeclaration,
    )

}


