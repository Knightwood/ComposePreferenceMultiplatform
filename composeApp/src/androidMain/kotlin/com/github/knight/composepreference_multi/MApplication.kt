package com.github.knight.composepreference_multi

import android.app.Application
import com.knightwood.floor.core.core.KVFloor
import com.knightwood.floor.mmkv.MMKVFloor
import com.tencent.mmkv.MMKV

class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mmkvFloor()
    }

    fun mmkvFloor() {
        MMKV.initialize(this)
        MMKVFloor.dbProvider(object : KVFloor.KVDBProvider<MMKV> {
            override fun get(name: String): MMKV {
                return MMKV.defaultMMKV()
            }
        })
    }
}
