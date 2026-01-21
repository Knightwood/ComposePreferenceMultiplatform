package knightwood.kv.aop.floorcore.processor

import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import knightwood.kv.aop.floorcore.annotation.KVStore

/**
 *
 * 处理注解、类、字段等通用信息。
 *
 * 具体的生成文件功能由其他module提供。
 *
 */
@AutoService(SymbolProcessor::class)
class AnnotationProcessor(val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    val logger  get() = environment.logger
    val codeGenerator get() = environment.codeGenerator

    override fun process(resolver: Resolver): List<KSAnnotated> {
        //所有标注了KVStore注解的符号信息
        val symbols = resolver.getSymbolsWithAnnotation(KVStore::class.qualifiedName!!)

        //处理注解
        symbols
            .filter { it is KSClassDeclaration && it.validate() }
            .forEach { it.accept(BuilderVisitor(), Unit) }

        val ret = symbols.filter { !it.validate() }.toList()
        return ret//返回无法处理的注解
    }

    inner class BuilderVisitor : KSVisitorVoid() {
        override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
            super.visitPropertyDeclaration(property, data)
        }
    }
}
