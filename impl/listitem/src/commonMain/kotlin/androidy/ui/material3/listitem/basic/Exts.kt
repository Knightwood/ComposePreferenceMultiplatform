package androidy.ui.material3.listitem.basic

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.isUnspecified
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 先条件判断，如果为真，则对传入参数调用then并返回调用then的结果。如果为假，直接返回传入参数。
 *  相当于：
 *  ```
 * return if(true) then(this) else this
 * ```
 *
 * 用法：
 * ```
 * modifier = Modifier
 *           .align(alignment)
 *           .takeIf({ leadingPercent != null }) {
 *               this.weight(leadingPercent!!)
 *           }
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

/**
 * 如果为null或者isUnspecified，则返回valueProvider()，否则返回this
 */
internal inline infix fun DpSize?.invalidUse(valueProvider: () -> DpSize): DpSize {
    return if (this == null || this.isUnspecified) valueProvider() else this
}

internal fun PaddingValues.copy(
    top: Dp = this.calculateTopPadding(),
    bottom: Dp = this.calculateBottomPadding(),
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    start: Dp = this.calculateStartPadding(layoutDirection),
    end: Dp = this.calculateEndPadding(layoutDirection),
): PaddingValues {
    return PaddingValues(top = top, bottom = bottom, start = start, end = end)
}
