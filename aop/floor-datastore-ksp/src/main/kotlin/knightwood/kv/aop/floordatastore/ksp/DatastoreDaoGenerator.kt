package knightwood.kv.aop.floordatastore.ksp

import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import knightwood.kv.aop.floorcore.annotation.KVStore
import knightwood.kv.aop.floorcore.ksp.writer.IDaoGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@AutoService(IDaoGenerator::class)
open class DatastoreDaoGenerator public constructor() : IDaoGenerator {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val dbtype: String = KVStore.DBType.DATASTORE

    private lateinit var codeGenerator: CodeGenerator
    private lateinit var kspLogger: KSPLogger

    override fun isSupport(dbtype: String): Boolean {
        return dbtype == this.dbtype
    }

    override fun setArg(
        kspLogger: KSPLogger,
        codeGenerator: CodeGenerator,
    ) {

        this.kspLogger = kspLogger
        this.codeGenerator = codeGenerator
    }

    override fun generateFile(classDeclaration: KSClassDeclaration) {

    }

    override fun fillFile(ksPropertyDeclaration: List<KSPropertyDeclaration>) {

    }
}
