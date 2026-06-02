package androidy.preference.helper.common

import kotlinx.coroutines.flow.Flow

interface IPreferenceProvider<T> {
    val flow: Flow<T>
    val value: T
    suspend fun update(value: T)
    suspend fun clear()
}
