package io.github.kalist28.bubbles

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.kalist28.bubbles.core.LocalContainerColor
import io.github.kalist28.bubbles.core.LocalContentColor
import io.github.kalist28.bubbles.core.LocalTextStyle
import io.github.kalist28.bubbles.core.theme.ColorScheme
import io.github.kalist28.bubbles.core.theme.LocalColorScheme
import io.github.kalist28.bubbles.core.theme.LocalTypography
import io.github.kalist28.bubbles.core.theme.Typography
import io.github.kalist28.bubbles.core.theme.darkColorScheme
import io.github.kalist28.bubbles.core.theme.lightColorScheme

@Composable
fun BubblesTheme(
    typography: Typography = LocalTypography.current,
    colorScheme: ColorScheme = run {
        if (isSystemInDarkTheme()) darkColorScheme()
        else lightColorScheme()
    },
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColorScheme provides colorScheme,
        LocalTextStyle provides typography.body,
        LocalContainerColor provides colorScheme.systemBackground,
        LocalContentColor provides colorScheme.label,
        content = content
    )
}