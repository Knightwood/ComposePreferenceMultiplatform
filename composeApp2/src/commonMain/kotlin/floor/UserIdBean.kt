package floor

import com.knightwood.floor.core.annotation.KVField
import com.knightwood.floor.core.annotation.KVStore
import java.util.UUID

@KVStore
@JvmInline
value class UserIdBean(@KVField(defaultValue = "\"5d14963a-2a32-4e91-ae8b-ab92a865cdc4\"") val id: UUID)


