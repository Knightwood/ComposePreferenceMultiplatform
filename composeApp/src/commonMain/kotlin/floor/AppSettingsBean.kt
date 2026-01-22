package floor

import knightwood.kv.aop.floorcore.annotation.KVStore

@KVStore(name = "app_settings")
data class AppSettingsBean(
    val isDebug:Boolean
)
