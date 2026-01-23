@file:OptIn(ExperimentalUuidApi::class)

package floor

import knightwood.kv.aop.floorcore.annotation.TypeConverter
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

object TypeConvertors {
    @TypeConverter
    fun uuid2String(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun string2Uuid(uuid: String): UUID {
        return UUID.fromString(uuid)
    }
}
