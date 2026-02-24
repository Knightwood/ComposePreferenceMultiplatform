import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.knightwood.floor.core.core.KVFloor
import com.knightwood.floor.datastore.DatastoreFloor
import floor.AppSettingsBean_Dao
import floor.UserIdBean
import floor.UserIdBean_Dao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import java.util.UUID

class MainViewModel {
    val desktopPath = System.getProperty("user.home") + "/Desktop"
    val dataStore = getDataStore("$desktopPath/ee.preferences_pb")
    val dao = UserIdBean_Dao()
    val dao2 = AppSettingsBean_Dao()
    val scope = CoroutineScope(Dispatchers.IO) + SupervisorJob()

    fun initAPP() {
        DatastoreFloor
            .dbProvider(
                object : KVFloor.KVDBProvider<DataStore<Preferences>> {
                    override fun get(name: String): DataStore<Preferences> {
                        return getDataStore(name)
                    }

                    override fun getDefault(): DataStore<Preferences> {
                        return dataStore
                    }
                }
            )
            .preLoad()
    }

    fun write() {
        scope.launch {
            dao.update(UserIdBean(UUID.randomUUID()))
        }
    }

    fun read() {
        scope.launch {
            val userId = dao.value
            println("userId: $userId")
        }
    }
}
