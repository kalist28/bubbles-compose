package io.github.kalist28.bubbles.core

import androidx.compose.runtime.Composable
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
val LocalContainerColor: ProvidableCompositionLocal<Color>
    @Composable
    @ReadOnlyComposable
    get() = LocalContainerColorProvider.current

val LocalContainerColorProvider = staticCompositionLocalOf { EmptyLocalColor }

private val EmptyLocalColor = compositionLocalOf { Color.Black }
