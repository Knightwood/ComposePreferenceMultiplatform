package knightwood.kv.aop.floorcore.annotation

/**
 * 用来标记每个data class
 * 被标记的data class 视为一个存储库
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class KVStore(
    val name: String = "",
    val type: KVStoreType = KVStoreType.DATASTORE,
)

enum class KVStoreType {
    DATASTORE,
    MMKV,
    PREFERENCE
}

/**
 * 用来标记data class的属性
 * 被标记的属性视为一个字段
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class KVField(
    val key: String = "",
    val default: String = "NULL",
)
