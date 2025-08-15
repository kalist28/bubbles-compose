package io.github.kalist28.bubbles.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle

/**
 * Interoperable composition local for text style.
 *
 * It used as source of local text style in all widgets.
 *
 * You can provide your own local (for ex. basic Material local) using [LocalTextStyleProvider]
 * */
val LocalTextStyle: ProvidableCompositionLocal<TextStyle>
    @Composable
    get() = LocalTextStyleProvider.current

val LocalTextStyleProvider = staticCompositionLocalOf { EmptyLocalTextStyle }

private val EmptyLocalTextStyle = compositionLocalOf { TextStyle.Default }