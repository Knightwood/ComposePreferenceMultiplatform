package com.knightwood.floor.core.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus

abstract class KVFloor<DATA_BASE> {
    val coroutineScope = CoroutineScope(Dispatchers.IO) + SupervisorJob()

    /**
     * 此接口实现将用于提供数据库实例
     */
    protected lateinit var dbProvider: KVDBProvider<DATA_BASE>

    /**
     * 默认数据库实例
     */
    protected val db: DATA_BASE by lazy { dbProvider.getDefault() }

    /**
     * 提供数据库实例缓存
     */
    protected val dbMap = mutableMapOf<String, DATA_BASE>()

    open fun dbProvider(dbProvider: KVDBProvider<DATA_BASE>): KVFloor<DATA_BASE> {
        this.dbProvider = dbProvider
        return this
    }

    /**
     * 使用此方法获取一个数据库实例。
     * 注意此方法要在[dbProvider]方法之后调用
     *
     * 如果实例不存在，则创建一个。避免同一个数据库名称创建多个实例。
     *
     * @param dbFileName 数据库名称 如果为空，则返回默认数据库
     */
    open fun getDb(dbFileName: String?): DATA_BASE {
        if (dbFileName.isNullOrBlank() || dbFileName == KVFloor.DEFAULT_DB_NAME) return db
        return dbMap.getOrPut(dbFileName) {
            dbProvider.get(dbFileName)
        }
    }

    companion object {
        const val DEFAULT_DB_NAME = "default_kvdb_name"
    }

    /**
     * 实现此类以提供数据库实例
     */
    interface KVDBProvider<T> {
        /**
         * 提供数据库实例
         *
         * 如果你不重写[getDefault]方法，应在传入name为[KVFloor.DEFAULT_DB_NAME]时应提供默认数据库
         *
         * 注意：
         * datastore
         * 同一个文件名：不能创建多个 DataStore 实例，否则会损坏数据
         * 不同文件名：可以创建多个 DataStore 实例
         *
         * @param name 数据库名称。
         */
        fun get(name: String): T

        fun getDefault(): T = get(KVFloor.DEFAULT_DB_NAME)
    }
}
