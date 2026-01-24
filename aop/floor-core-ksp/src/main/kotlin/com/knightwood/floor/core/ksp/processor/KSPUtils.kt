package com.knightwood.floor.core.ksp.processor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.Modifier
import kotlin.reflect.KClass

// 判断是否是数据类
fun KSClassDeclaration.isDataClass(): Boolean {
    return this.modifiers.contains(Modifier.DATA)
}

// 获取包名
val KSClassDeclaration.packageName: String
    get() = this.containingFile?.packageName?.asString() ?: ""

// 获取类
fun ksClassDeclarationToKClass(ksClassDeclaration: KSClassDeclaration): KClass<*>? {
    val qualifiedName = ksClassDeclaration.qualifiedName?.asString()
    return if (qualifiedName != null) {
        try {
            Class.forName(qualifiedName).kotlin
        } catch (e: ClassNotFoundException) {
            null
        }
    } else {
        null
    }
}

/**
 * 获取注解参数值
 * @param name 参数名
 * 例如
 * @KVStore(name = "user.pb", daoName = "UserDao")
 * name参数的值为"user.pb"
 * daoName参数的值为"UserDao"
 */
fun KSAnnotation.getArgumentValue(name: String): String? {
    return arguments.find { argument -> argument.name?.asString() == name }?.value as? String
}

/**
 *  判断属性是否为nullable
 */
fun KSPropertyDeclaration.isNullable(): Boolean {
    return this.type.resolve().isMarkedNullable
}
