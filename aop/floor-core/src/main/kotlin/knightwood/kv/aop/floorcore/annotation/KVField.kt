package knightwood.kv.aop.floorcore.annotation

/**
 * 此注解用于标记类中的变量，被标记的变量将视为某个键的映射，该变量将存储某键在数据库中的值
 *
 * 默认值有如下几种格式
 * ```
 * @KVField(defaultValue = "") -> 没默认值
 * @KVField(defaultValue = "\"abc\"") -> "abc"
 * @KVField(defaultValue = "\"\"") -> ""
 * @KVField(defaultValue = "null") -> null
 * @KVField(defaultValue = "12") -> 12
 *
 * ```
 * @param key 键名，默认值空字符串--生成的键名格式：“类名_变量名”
 * @param defaultValue 默认值，当数据库中不存在此键时，将使用此默认值。
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class KVField(
    val key: String = "",
    val defaultValue: String = "",
)
