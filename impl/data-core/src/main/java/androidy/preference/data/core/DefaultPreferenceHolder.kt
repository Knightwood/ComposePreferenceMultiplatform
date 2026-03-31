package androidy.preference.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

/**
 * 默认的偏好值存储工具，其实他根本不会存储偏好值。
 */
class DefaultPreferenceHolder internal constructor() : AbstractPreferenceHolder() {
    override val editorProvider: ISinglePrefValueEditorProvider = object : ISinglePrefValueEditorProvider {
        override fun <T : Any> createOnePrefEditor(
            keyName: String,
            defaultValue: T,
            cls: KClass<T>,
        ): ISinglePrefValueEditor<T> {
            return FakeEditor(keyName, defaultValue)
        }
    }

    companion object {
        @Volatile
        var ps: DefaultPreferenceHolder? = null
        fun instance(
        ): DefaultPreferenceHolder {
            return ps ?: synchronized(this) {
                ps ?: DefaultPreferenceHolder().also { ps = it }
            }
        }
    }
}

class FakeEditor<T : Any>(
    val keyName: String,
    val defaultValue: T,
) : ISinglePrefValueEditor<T> {
    private val stateFlow: MutableStateFlow<T> = MutableStateFlow(defaultValue)

    override fun flow(): Flow<T> {
        return stateFlow
    }

    override fun readValue(): T {
        return stateFlow.value
    }

    override suspend fun write(data: T?) {
        if (data == null) {
            stateFlow.emit(defaultValue)
        } else {
            this.stateFlow.emit(data)
        }
    }
}
