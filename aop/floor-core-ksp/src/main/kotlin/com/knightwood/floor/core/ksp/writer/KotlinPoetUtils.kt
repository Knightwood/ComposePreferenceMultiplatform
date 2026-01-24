package com.knightwood.floor.core.ksp.writer

import com.squareup.kotlinpoet.ClassName


/**
 * Int和Int?是不同的类型表示
 *
 * 此方法将从原有的ClasName创建一个nullable的ClassName
 */
fun ClassName.nullable() : ClassName = this.copy(nullable = true) as ClassName
