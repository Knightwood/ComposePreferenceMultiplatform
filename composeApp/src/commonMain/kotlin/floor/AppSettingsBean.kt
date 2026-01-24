@file:OptIn(ExperimentalUuidApi::class)

package floor

import com.knightwood.floor.core.annotation.KVField
import com.knightwood.floor.core.annotation.KVStore
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi

@KVStore(name = "app_settings")
data class AppSettingsBean(
    @KVField(defaultValue = "false")
    val isDebug: Boolean,
    @KVField(defaultValue = "\"uui\"")
    val name: String,
    @KVField(defaultValue = "12", key = "fuck_age")
    val age: Int,
    @KVField()
    val sex: Int,
    @KVField()
    val id: UUID,
)
