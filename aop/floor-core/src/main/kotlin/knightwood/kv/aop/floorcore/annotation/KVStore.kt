package knightwood.kv.aop.floorcore.annotation

/**
 *
 * 一些在此库中的描述：
 * 1. mmkv、datastore都可存储键值对数据，称键值对数据库
 * 2. 我们可能会对键值对分组，比如用户信息相关的N个键值对为一组、app主体信息相关的N个键值对为一组
 * 3. 我们可能将不同组的键值对分别存入不同文件，比如user.pb存储用户相关键值对，config.pb存储app主题配置的键值对
 * 4. 我们也可能将不同组的键值对存入同一个文件，比如用一个app.pb存储用户、app主题配置等的键值对
 * 5. 我们使用键值对数据库时可能会读取1~N个键对应的值，直接使用或者将这组键对应的值存入一个data class
 *  示例：
 *  ```
 *  读取1个键对应的值，直接使用：
 *  val name = kvStore.getString("name")
 *  读取N个键对应的值，存入data class：
 *  val name = kvStore.getString("name")
 *  val age = kvStore.getInt("age")
 *  val user = User(name,age)
 *  ```
 *
 *
 *  我们定义一个注解KVStore，用此注解标记的类视作一组键值对数据库中键的映射集合。
 *  该类中使用KVField注解标记的变量，将视作某个键的映射，变量的值存储键对应的值。
 *  由于键值对数据库是非结构化的，一个键的映射（KVField标记的字段）可以存在于多个KVStore标记的类。
 *  一个KVStore标记的类，其所有的KVField标注的字段都将属于一个文件。
 *  我们将一组键值对映射到了一个类中，该组键值对的读取、写入、更新、删除将由一个Dao类负责
 *
 * @param name datastore文件名，默认为空，使用默认的datastore文件
 * @param daoName 最终生成的dao类名，默认为data class名+Dao
 * @param type 所用键值对数据库类型
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class KVStore(
    val name: String = "",
    val daoName: String = "",
    val type: String = DBType.DATASTORE,
) {
    companion object DBType{
        const val DATASTORE = "ds"
        const val MMKV = "mmkv"
        const val PREFERENCE = "preference"
    }
}
