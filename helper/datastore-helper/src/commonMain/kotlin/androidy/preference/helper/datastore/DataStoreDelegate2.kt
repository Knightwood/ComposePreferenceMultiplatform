package androidy.preference.helper.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.runBlocking
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

object DataStoreCoroutineScopeHolder {
    lateinit var scope: CoroutineScope
    fun get(): CoroutineScope {
        if (!this::scope.isInitialized) {
            scope = CoroutineScope(Dispatchers.IO) + SupervisorJob()
        }
        return scope
    }

    fun destroy() {
        scope.cancel()
    }

    fun set(scope: CoroutineScope) {
        this.scope = scope
    }
}
//<editor-fold desc="DataStoreDelegate2 nullable">

open class DataStoreDelegate2<T>(
    val dataStore: DataStore<Preferences>,
    val keyName: String,
    val defaultValue: T? = null,
    type: KClass<*>,
) {
    val key = DataStoreKeyUtils.getKey<T>(keyName = keyName, type)

    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return runBlocking {
            dataStore.data.map { preferences ->
                preferences[key]
            }.firstOrNull() ?: defaultValue
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        DataStoreCoroutineScopeHolder.get().launch {
            dataStore.edit { preferences ->
                if (value != null) {
                    preferences[key] = value
                } else {
                    preferences.remove(key)
                }
            }
        }
    }
}


class DatastoreDelegate2Provider<T>(
    val dataStore: DataStore<Preferences>,
    val keyName: String?,
    val defaultValue: T? = null,
    val type: KClass<*>,
) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): DataStoreDelegate2<T> {
        return DataStoreDelegate2(
            dataStore = dataStore,
            defaultValue = defaultValue,
            keyName = keyName ?: property.name,
            type = type
        )
    }
}

/**
 * ```
 *
 * class DatastoreTest(val dataStore: DataStore<Preferences>) {
 *     var username: String? by dataStore.getting<String>()
 *     var age: Int by dataStore.gettingNoNull<Int>(12, "age")
 * }
 * ```
 */
inline fun <reified T> DataStore<Preferences>.getting(
    defaultValue: T? = null,
    keyName: String? = null,
): DatastoreDelegate2Provider<T> {
    return DatastoreDelegate2Provider(
        dataStore = this,
        keyName = keyName,
        defaultValue = defaultValue,
        type = T::class
    )
}

//</editor-fold>
//<editor-fold desc=" DataStoreDelegate2NoNull">

class DataStoreDelegate2NoNull<T>(
    dataStore: DataStore<Preferences>,
    keyName: String,
    type: KClass<*>,
    defaultValue: T,
) : DataStoreDelegate2<T>(dataStore = dataStore, defaultValue = defaultValue, keyName = keyName, type = type) {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return runBlocking {
            (dataStore.data.map { preferences ->
                preferences[key]
            }.firstOrNull() ?: defaultValue)!!
        }
    }

}

class DatastoreDelegate2NoNullProvider<T>(
    val dataStore: DataStore<Preferences>,
    val keyName: String?,
    val defaultValue: T,
    val type: KClass<*>,
) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): DataStoreDelegate2NoNull<T> {
        return DataStoreDelegate2NoNull(
            dataStore = dataStore,
            keyName = keyName ?: property.name,
            defaultValue = defaultValue,
            type = type
        )
    }
}
//</editor-fold>


/**
 * ```
 *
 * class DatastoreTest(val dataStore: DataStore<Preferences>) {
 *     var username: String? by dataStore.getting<String>()
 *     var age: Int by dataStore.gettingNoNull<Int>(12, "age")
 * }
 * ```
 */
inline fun <reified T> DataStore<Preferences>.gettingNoNull(
    defaultValue: T,
    keyName: String? = null,
): DatastoreDelegate2NoNullProvider<T> {
    return DatastoreDelegate2NoNullProvider(
        dataStore = this,
        keyName = keyName,
        defaultValue = defaultValue,
        type = T::class
    )
}
