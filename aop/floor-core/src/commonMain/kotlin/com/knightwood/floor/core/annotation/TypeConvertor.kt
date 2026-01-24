package com.knightwood.floor.core.annotation

/**
 *
 * Marks a method as a type converter. A class can have as many @TypeConverter methods as it needs.
 * Each converter method should receive 1 parameter and have non-void return type.
 *
 * 由于无法预先知道键值对数据库中某键的类型，
 * 只能支持非基本类型转换到String类型，String类型转换到非基本类型
 *
 * 1. 对于变量类型不可为null
 *
 * val date :Date
 *
 * 非基本类型->String类型 映射方法
 * ```
 * @TypeConverter
 * public fun dateToTimestamp(date: Date): String
 * ```
 *
 * String类型->非基本类型 映射方法
 * ```
 *@TypeConverter
 *public fun fromTimestamp(value: String): Date
 * ```
 *
 *
 * 2. 对于变量类型可为null
 *
 * 写入kv数据库时，需要将类型转换成String类型，这个String类型是否为null是可以被处理的
 * 比如
 * DatastoreUtils.setOrRemove
 * MMKVEditor.write
 * 都可以完成在输入值为null时清除key的目的
 * 因此转换函数的入参必须是非Nullable的，返回值可以为String也可以为String?，但是我们规定为String?
 *
 * 从数据库读取出来给变量赋值，需要将String类型转换成非基本类型
 * 读取出来的值未必存在，默认值也未必存在。变量类型又是nullable的
 * 因此转换函数的入参必须是String?，返回值也自然得是Nullable的。
 *
 * val date :Date?
 *
 * 非基本类型->String?类型 映射方法
 * ```
 * @TypeConverter
 * public fun dateToTimestamp(date: Date?): String?
 * ```
 *
 * String类型->非基本类型 映射方法
 * ```
 *@TypeConverter
 *public fun fromTimestamp(value: String?): Date?
 * ```
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
public annotation class TypeConverter
