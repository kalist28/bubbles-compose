package io.github.kalist28.bubbles.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Interoperable composition local for content color.
 *
 * It used as source of local content color in all widgets.
 *
 * You can provide your own local (for ex. basic Material local) using [LocalContentColorProvider]
 * */
val LocalContentColor: ProvidableCompositionLocal<Color>
    @Composable
    @ReadOnlyComposable
    get() = LocalContentColorProvider.current

@Composable
fun ProvideContentColor(
    color: Color,
    content: @Composable () -> Unit,
) = CompositionLocalProvider(
    LocalContentColor provides color,
    content = content
)

val LocalContentColorProvider =
    staticCompositionLocalOf {
        EmptyLocalColor
    }

private val EmptyLocalColor =
    compositionLocalOf {
        Color.Black
    }
