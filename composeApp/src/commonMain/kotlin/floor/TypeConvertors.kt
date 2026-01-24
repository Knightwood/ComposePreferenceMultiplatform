@file:OptIn(ExperimentalUuidApi::class)

package floor

import com.knightwood.floor.core.annotation.TypeConverter
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi

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
