package androidy.preference.ui.basic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 先条件判断，如果为真，则对传入参数调用then并返回调用then的结果。如果为假，直接返回传入参数。
 *  相当于：
 *  ```
 * return if(true) then(this) else this
 * ```
 * @param predicate
 * @param then
 * @return
 */
@OptIn(ExperimentalContracts::class)
@SinceKotlin("1.1")
public inline fun <T> T.takeIf(
    predicate: () -> Boolean,
    then: T.() -> T,
): T {
    contract {
        callsInPlace(predicate, InvocationKind.EXACTLY_ONCE)
    }
    return if (predicate()) this.then() else this
}

/**
 * 如果为null，则返回valueProvider()，否则返回this
 */
internal inline infix fun <T> T?.isNullUse(
    valueProvider: () -> T,
): T {
    return this ?: valueProvider()
}

/**
 * 如果为null或者isUnspecified，则返回valueProvider()，否则返回this
 */
internal inline infix fun Color?.invalidUse(valueProvider: () -> Color): Color {
    return if (this == null || this.isUnspecified) valueProvider() else this
}
