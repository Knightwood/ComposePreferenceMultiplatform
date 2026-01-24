package floor

import com.knightwood.floor.core.annotation.KVField
import com.knightwood.floor.core.annotation.KVStore
import java.util.UUID

@KVStore
@JvmInline
value class UserIdBean(@KVField val id: UUID)


