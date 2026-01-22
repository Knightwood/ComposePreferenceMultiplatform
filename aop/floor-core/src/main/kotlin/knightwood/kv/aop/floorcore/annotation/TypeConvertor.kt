package knightwood.kv.aop.floorcore.annotation

/**
 *
 * Marks a method as a type converter. A class can have as many @TypeConverter methods as it needs.
 * Each converter method should receive 1 parameter and have non-void return type.
 *
 * ```
 * // example converter for java.util.Date
 * public class Converters {
 *     @TypeConverter
 *     public fun fromTimestamp(value: Long): Date {
 *         return value == null ? null : Date(value)
 *     }
 *
 *     @TypeConverter
 *     public fun dateToTimestamp(date: Date): Long {
 *         if (date == null) {
 *             return null
 *         } else {
 *             return date.getTime()
 *         }
 *     }
 * }
 * ```
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
public annotation class TypeConverter
