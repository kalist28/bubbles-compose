package io.github.kalist28.bubbles.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import io.github.kalist28.bubbles.core.theme.BubblesColors
import io.github.kalist28.bubbles.core.theme.White

/**
 * This object provides access to native accessibility preferences
 * */
object Accessibility

expect val Accessibility.isHighContrastEnabled: Boolean

expect val Accessibility.isReduceTransparencyEnabled: Boolean

/**
 * Adjust color contrast if corresponding OS accessibility system preference is enabled
 * */
val Color.accessible: Color
    @Composable
    get() = accessible(isDark())

/**
 * Adjust color contrast if corresponding accessibility system preference is enabled
 * */
fun Color.accessible(isDark: Boolean): Color =
    if (Accessibility.isHighContrastEnabled) {
        lerp(this, if (isDark) BubblesColors.White else Color.Black, .2f)
    } else {
        this
    }
