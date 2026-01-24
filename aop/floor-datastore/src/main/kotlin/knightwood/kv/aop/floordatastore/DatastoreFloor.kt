package knightwood.kv.aop.floordatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import knightwood.kv.aop.floorcore.core.KVFloor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 设计
 * 一个应用中可以有多个datastore实例
 * 不同的字段可以存储在不同datastore实例中，比如：
 *      用户相关kv存储在名为user的datastore文件
 *      app配置相关kv存储在名为config的datastore文件
 * 一组配置
 *
 * 1. 每个标记了KVStore的data class，都对应一个DatastoreDao实例。
 *
 *
 * 用于初始化Datastore，提供默认数据库实例
 *
 */
object DatastoreFloor : KVFloor<DataStore<Preferences>>() {
    override fun dbProvider(dbProvider: KVDBProvider<DataStore<Preferences>>): DatastoreFloor {
        super.dbProvider(dbProvider)
        return this
    }

    /**
     * 此函数的调用要在[dbProvider]之后，避免调用此方法时还没有创建db实例
     */
    fun preLoad() {
        coroutineScope.launch {
            db.data.first()    //预加载
        }
    }
}
