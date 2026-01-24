package com.knightwood.floor.core.annotation

/**
 *
 * Marks a method as a type converter. A class can have as many @TypeConverter methods as it needs.
 * Each converter method should receive 1 parameter and have non-void return type.
 *
 * 由于无法预先知道键值对数据库中某键的类型，
 * 只能支持非基本类型转换到String类型，String类型转换到非基本类型
 *
 * 非基本类型->String类型 映射方法
 *
 * ```
 *     @TypeConverter
 *     public fun dateToTimestamp(date: Date): String {
 *         if (date == null) {
 *             return null
 *         } else {
 *             return date.getTime().toString()
 *         }
 *     }
 * ```
 *
 * String类型->非基本类型 映射方法
 *
 * ```
 *     @TypeConverter
 *     public fun fromTimestamp(value: String): Date {
 *         return value == null ? null : Date(value)
 *     }
 * ```
 *
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
public annotation class TypeConverter
