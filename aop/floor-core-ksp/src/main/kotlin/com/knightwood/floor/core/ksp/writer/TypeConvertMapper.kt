package com.knightwood.floor.core.ksp.writer

import com.google.devtools.ksp.isPublic
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ksp.toClassName
import com.knightwood.floor.core.annotation.TypeConverter

object TypeConvertMapper {

    /**
     * Map<Pair<输入参数类型，输出返回值类型>,方法定义>
     */
    val map = mutableMapOf<Pair<ClassName, ClassName>, KSFunctionDeclaration>()

    /**
     * 找到所有标记了@TypeConverter注解的方法，将方法信息保存到map中
     */
    fun process(resolver: Resolver) {
        val symbols = resolver.getSymbolsWithAnnotation(TypeConverter::class.qualifiedName!!)
            .filterIsInstance<KSFunctionDeclaration>()
            .filter { declaration -> declaration.validate() && declaration.isPublic() }
            .toList()

        for (functionDeclaration in symbols) {
            val outputType = functionDeclaration.returnType?.resolve()?.toClassName()
                ?: error("${functionDeclaration.qualifiedName}.returnType is null")
            val inputType = functionDeclaration.parameters.firstOrNull()?.type?.resolve()?.toClassName()
                ?: error("${functionDeclaration.qualifiedName}.parameters.firstOrNull()?.type is null")
            map.putIfAbsent(inputType to outputType, functionDeclaration)
        }
    }
}
