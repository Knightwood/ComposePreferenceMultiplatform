package androidy.preference.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

class EmptyValueEditorHodler : AbstractValueEditorHolder() {
    override val editorProvider: ISinglePrefValueEditorProvider = object : ISinglePrefValueEditorProvider {
        override fun <T : Any> createOnePrefEditor(
            keyName: String,
            cls: KClass<T>,
        ): ISingleValueEditor<T> {
            return object : ISingleValueEditor<T> {
                val _flow = MutableStateFlow<T?>(null)
                override val flow: Flow<T?> get() = _flow

                override suspend fun readValueAsync(): T? {
                    return _flow.value
                }

                override suspend fun writeAsync(data: T?) {
                    _flow.value = data
                }
            }
        }
    }
}
