package floor

import knightwood.kv.aop.floorcore.annotation.KVField
import knightwood.kv.aop.floorcore.annotation.KVStore
import java.util.UUID

@KVStore
@JvmInline
value class UserIdBean(@KVField val id: UUID)


