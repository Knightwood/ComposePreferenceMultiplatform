package com.github.knight.composepreference_multi

import com.knightwood.floor.core.annotation.KVField
import com.knightwood.floor.core.annotation.KVStore
import java.util.UUID


@KVStore(type = KVStore.MMKV)
data class MMKVBean(
    @KVField(defaultValue = "\"jack\"") val name: String,
    @KVField val age: Int?,
    /**
     * 非基本类型需要使用TypeConvertor转换成字符串存储
     * 所以这里的默认值需要使用字符串
     */
    @KVField(defaultValue = "\"5d14963a-2a32-4e91-ae8b-ab92a865cdc4\"") val id : UUID
)
