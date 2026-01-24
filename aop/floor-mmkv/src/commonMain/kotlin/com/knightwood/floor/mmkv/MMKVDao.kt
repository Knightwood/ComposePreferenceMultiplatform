package com.knightwood.floor.mmkv

import com.knightwood.floor.core.core.IPreferenceDao
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * @param dbFileName MMKV文件名
 */
abstract class MMKVDao<T : Any>(val dbFileName: String? = null) : IPreferenceDao<T> {
    protected val mmkv: MMKV by lazy {
        MMKVFloor.getDb(dbFileName)
    }
    private val _flow = MutableSharedFlow<T>(replay = 1)
    override val flow: Flow<T> get() = _flow

    override val value: T
        get() = mmkv.asT()

    init {
        updateFlow()
    }

    private fun updateFlow() {
        MMKVFloor.coroutineScope.launch {
            _flow.emit(mmkv.asT())
        }
    }

    override suspend fun update(value: T) {
        value.modify(mmkv)
        updateFlow()
    }

    override suspend fun clear() {
        keys.forEach { key ->
            mmkv.remove(key)
        }
    }

    /**
     * 存储此存储库中所有的key
     */
    abstract val keys: List<String>

    /**
     * 用于将Preferences数据转换成T
     */
    abstract fun MMKV.asT(): T

    /**
     * 用于将T数据转换成Preferences
     */
    abstract fun T.modify(target: MMKV)
}
