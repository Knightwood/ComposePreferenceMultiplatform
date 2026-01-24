package com.knightwood.floor.mmkv

import com.knightwood.floor.core.core.KVFloor
import com.tencent.mmkv.MMKV


object MMKVFloor : KVFloor<MMKV>() {
    override fun dbProvider(dbProvider: KVDBProvider<MMKV>): MMKVFloor {
        super.dbProvider(dbProvider)
        return this
    }
}
