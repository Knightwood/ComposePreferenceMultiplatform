package com.github.knight.composepreference_multi

import com.knightwood.floor.core.annotation.KVField
import com.knightwood.floor.core.annotation.KVStore
import java.util.UUID


@KVStore(type = KVStore.MMKV)
data class MMKVBean(
    @KVField(defaultValue = "\"jack\"") val name: String,
    @KVField val age: Int?,
    @KVField(defaultValue = "\"5d14963a-2a32-4e91-ae8b-ab92a865cdc4\"") val id : UUID
)
