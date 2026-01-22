使用Datastore或类似的kv存储工具时，想象我们有如下情景：

1. 使用user.pb文件存储存储用户信息，包括用户ID、用户名、用户邮箱等。
2. 使用theme.pb文件存储app的主题信息，包括主题ID、主题名称、主题颜色等。
3. 使用数据类将零碎的读写封装起来

例如：
```kotlin

data class User(val id: Int, val name: String, val email: String)
data class Theme(val id: Int, val name: String, val color: String)

object UserStore {
    val ds = getDatastore("users.pb")
    
    val userIdKey = intPreferencesKey("userId")
    val userNameKey = stringPreferencesKey("userName")
    val userEmailKey = stringPreferencesKey("userEmail")
    val userKeys = listOf(userIdKey, userNameKey, userEmailKey)
    
    // 保存用户信息
    suspend fun save(user: User) {
        ds.edit{preference->
            preference[userIdKey] = user.id
            preference[userNameKey] = user.name
            preference[userEmailKey] = user.email
        }
    }
    
    // 读取用户信息
    val flow = ds.data.map{preference->
        User(
            id = preference[userIdKey] ?: -1,
            name = preference[userNameKey] ?: "",
            email = preference[userEmailKey] ?: ""
        )
    }
}

//与上面同理，不再赘述
object ThemeStore
```

现在，使用floor对这一过程进行处理。

```kotlin
@KVStore("user.pb")
data class User(
   @KVField(key = "userId") val id: Int,
   @KVField(key = "userName")  val name: String,
   @KVField(key = "userEmail")  val email: String)

val dao=DatastoreFloor.userDao()

//保存用户信息
dao.update(User(1,"张三","zhangsan@example.com"))
//读取用户信息
dao.flow.collect{
    
}
//同步读取
dao.value
//清空
dao.clear()
```
