package io.github.kalist28.bubbles.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import io.github.kalist28.bubbles.core.Accessibility
import io.github.kalist28.bubbles.core.isHighContrastEnabled

/**
 * Immutable set of semantic colors used across the library.
 *
 * Use [lightColorScheme] and [darkColorScheme] to create instances.
 * Prefer copying with [copy] to tweak a few values while keeping the rest intact.
 */
@Immutable
class ColorScheme internal constructor(
    val isDark: Boolean,
    val accent: Color,
    val label: Color,
    val secondaryLabel: Color,
    val tertiaryLabel: Color,
    val quaternaryLabel: Color,
    val systemFill: Color,
    val secondarySystemFill: Color,
    val tertiarySystemFill: Color,
    val quaternarySystemFill: Color,
    val placeholderText: Color,
    val separator: Color,
    val opaqueSeparator: Color,
    val link: Color,
    val systemGroupedBackground: Color,
    val secondarySystemGroupedBackground: Color,
    val tertiarySystemGroupedBackground: Color,
    val systemBackground: Color,
    val secondarySystemBackground: Color,
    val tertiarySystemBackground: Color,
) {
    fun copy(
        accent: Color = this.accent,
        label: Color = this.label,
        secondaryLabel: Color = this.secondaryLabel,
        tertiaryLabel: Color = this.tertiaryLabel,
        quaternaryLabel: Color = this.quaternaryLabel,
        systemFill: Color = this.systemFill,
        secondarySystemFill: Color = this.secondarySystemFill,
        tertiarySystemFill: Color = this.tertiarySystemFill,
        quaternarySystemFill: Color = this.quaternarySystemFill,
        placeholderText: Color = this.placeholderText,
        separator: Color = this.separator,
        opaqueSeparator: Color = this.opaqueSeparator,
        link: Color = this.link,
        systemGroupedBackground: Color = this.systemGroupedBackground,
        secondarySystemGroupedBackground: Color = this.secondarySystemGroupedBackground,
        tertiarySystemGroupedBackground: Color = this.tertiarySystemGroupedBackground,
        systemBackground: Color = this.systemBackground,
        secondarySystemBackground: Color = this.secondarySystemBackground,
        tertiarySystemBackground: Color = this.tertiarySystemBackground,
    ) = ColorScheme(
        isDark = isDark,
        accent = accent,
        label = label,
        secondaryLabel = secondaryLabel,
        tertiaryLabel = tertiaryLabel,
        quaternaryLabel = quaternaryLabel,
        systemFill = systemFill,
        secondarySystemFill = secondarySystemFill,
        tertiarySystemFill = tertiarySystemFill,
        quaternarySystemFill = quaternarySystemFill,
        placeholderText = placeholderText,
        separator = separator,
        opaqueSeparator = opaqueSeparator,
        link = link,
        systemGroupedBackground = systemGroupedBackground,
        secondarySystemGroupedBackground = secondarySystemGroupedBackground,
        tertiarySystemGroupedBackground = tertiarySystemGroupedBackground,
        systemBackground = systemBackground,
        secondarySystemBackground = secondarySystemBackground,
        tertiarySystemBackground = tertiarySystemBackground,
    )
}

val LocalColorScheme = staticCompositionLocalOf { defaultBubblesColorScheme }

private val defaultBubblesColorScheme = lightColorScheme()

@Composable
@ReadOnlyComposable
fun isInitializedTheme(): Boolean = LocalColorScheme.current !== defaultBubblesColorScheme

@Composable
@ReadOnlyComposable
internal fun isDark(): Boolean = BubblesTheme.colorScheme.isDark

fun lightColorScheme(
    accent: Color = ColorSchemeTokens.lightAccent,
    label: Color = ColorSchemeTokens.lightLabel,
    secondaryLabel: Color = ColorSchemeTokens.lightSecondaryLabel,
    tertiaryLabel: Color = ColorSchemeTokens.lightTertiaryLabel,
    quaternaryLabel: Color = ColorSchemeTokens.lightQuaternaryLabel,
    systemFill: Color = ColorSchemeTokens.lightSystemFill,
    secondarySystemFill: Color = ColorSchemeTokens.lightSecondarySystemFill,
    tertiarySystemFill: Color = ColorSchemeTokens.lightTertiarySystemFill,
    quaternarySystemFill: Color = ColorSchemeTokens.lightQuaternarySystemFill,
    placeholderText: Color = ColorSchemeTokens.lightPlaceholderText,
    separator: Color = ColorSchemeTokens.lightSeparator,
    opaqueSeparator: Color = ColorSchemeTokens.lightOpaqueSeparator,
    link: Color = ColorSchemeTokens.lightLink,
    systemGroupedBackground: Color = ColorSchemeTokens.lightSystemGroupedBackground,
    secondarySystemGroupedBackground: Color = ColorSchemeTokens.lightSecondarySystemGroupedBackground,
    tertiarySystemGroupedBackground: Color = ColorSchemeTokens.lightTertiarySystemGroupedBackground,
    systemBackground: Color = ColorSchemeTokens.lightSystemBackground,
    secondarySystemBackground: Color = ColorSchemeTokens.lightSecondarySystemBackground,
    tertiarySystemBackground: Color = ColorSchemeTokens.lightTertiarySystemBackground,
): ColorScheme = ColorScheme(
    isDark = false,
    accent = accent,
    label = label,
    secondaryLabel = secondaryLabel,
    tertiaryLabel = tertiaryLabel,
    quaternaryLabel = quaternaryLabel,
    placeholderText = placeholderText,
    systemFill = systemFill,
    secondarySystemFill = secondarySystemFill,
    tertiarySystemFill = tertiarySystemFill,
    quaternarySystemFill = quaternarySystemFill,
    separator = separator,
    opaqueSeparator = opaqueSeparator,
    link = link,
    systemGroupedBackground = systemGroupedBackground,
    secondarySystemGroupedBackground = secondarySystemGroupedBackground,
    tertiarySystemGroupedBackground = tertiarySystemGroupedBackground,
    systemBackground = systemBackground,
    secondarySystemBackground = secondarySystemBackground,
    tertiarySystemBackground = tertiarySystemBackground,
)

fun darkColorScheme(
    accent: Color = ColorSchemeTokens.darkAccent,
    label: Color = ColorSchemeTokens.darkLabel,
    secondaryLabel: Color = ColorSchemeTokens.darkSecondaryLabel,
    tertiaryLabel: Color = ColorSchemeTokens.darkTertiaryLabel,
    quaternaryLabel: Color = ColorSchemeTokens.darkQuaternaryLabel,
    systemFill: Color = ColorSchemeTokens.darkSystemFill,
    secondarySystemFill: Color = ColorSchemeTokens.darkSecondarySystemFill,
    tertiarySystemFill: Color = ColorSchemeTokens.darkTertiarySystemFill,
    quaternarySystemFill: Color = ColorSchemeTokens.darkQuaternarySystemFill,
    placeholderText: Color = ColorSchemeTokens.darkPlaceholderText,
    separator: Color = ColorSchemeTokens.darkSeparator,
    opaqueSeparator: Color = ColorSchemeTokens.darkOpaqueSeparator,
    link: Color = ColorSchemeTokens.darkLink,
    systemGroupedBackground: Color = ColorSchemeTokens.darkSystemGroupedBackground,
    secondarySystemGroupedBackground: Color = ColorSchemeTokens.darkSecondarySystemGroupedBackground,
    tertiarySystemGroupedBackground: Color = ColorSchemeTokens.darkTertiarySystemGroupedBackground,
    systemBackground: Color = ColorSchemeTokens.darkSystemBackground,
    secondarySystemBackground: Color = ColorSchemeTokens.darkSecondarySystemBackground,
    tertiarySystemBackground: Color = ColorSchemeTokens.darkTertiarySystemBackground,
): ColorScheme = ColorScheme(
    isDark = true,
    accent = accent,
    label = label,
    secondaryLabel = secondaryLabel,
    tertiaryLabel = tertiaryLabel,
    quaternaryLabel = quaternaryLabel,
    placeholderText = placeholderText,
    systemFill = systemFill,
    secondarySystemFill = secondarySystemFill,
    tertiarySystemFill = tertiarySystemFill,
    quaternarySystemFill = quaternarySystemFill,
    separator = separator,
    opaqueSeparator = opaqueSeparator,
    link = link,
    systemGroupedBackground = systemGroupedBackground,
    secondarySystemGroupedBackground = secondarySystemGroupedBackground,
    tertiarySystemGroupedBackground = tertiarySystemGroupedBackground,
    systemBackground = systemBackground,
    secondarySystemBackground = secondarySystemBackground,
    tertiarySystemBackground = tertiarySystemBackground,
)

internal object ColorSchemeTokens {
    val lightAccent: Color = BubblesColors.systemBlue(false)
    val lightLabel: Color = Color.Black
    val lightSecondaryLabel: Color = Color(0xFF96A6C2) orContrast Color(0xcc3c3c43)
    val lightTertiaryLabel: Color = Color(0x4c3c3c43) orContrast Color(0xb23c3c43)

    val lightQuaternaryLabel: Color = Color(0x2d3c3c43) orContrast Color(0x8c3c3c43)
    val lightSystemFill: Color = Color(0x5b787880) orContrast Color(0x47787880)
    val lightSecondarySystemFill: Color = Color(0x51787880) orContrast Color(0x3d787880)

    val lightTertiarySystemFill: Color = Color(0x3d767680) orContrast Color(0x33767680)
    val lightQuaternarySystemFill: Color = Color(0x2d767680) orContrast Color(0x28747480)
    val lightPlaceholderText: Color = Color(0x4c3c3c43) orContrast Color(0xb23c3c43)
    val lightSeparator: Color = Color(0x493c3c43) orContrast Color(0x5e3c3c43)
    val lightOpaqueSeparator: Color = Color(0xffc6c6c8) orContrast Color(0xffc6c6c8)

    val lightLink: Color = Color(0xff007aff)
    val lightSystemGroupedBackground: Color = Color(0xfff2f2f7) orContrast Color(0xffebebf0)

    val lightSecondarySystemGroupedBackground: Color = Color.White
    val lightTertiarySystemGroupedBackground: Color = Color(0xfff2f2f7) orContrast Color(0xffebebf0)

    val lightSystemBackground: Color = Color.White
    val lightSecondarySystemBackground: Color = Color(0xfff2f2f7) orContrast Color(0xffebebf0)

    val lightTertiarySystemBackground: Color = Color.White

    val darkAccent: Color = BubblesColors.systemBlue(true)
    val darkLabel: Color = Color.White
    val darkSecondaryLabel: Color = Color(0x9996A6C2) orContrast Color(0xb2ebebf5)
    val darkTertiaryLabel: Color = Color(0x4cebebf5) orContrast Color(0x8cebebf5)
    val darkQuaternaryLabel: Color = Color(0x28ebebf5) orContrast Color(0x66ebebf5)
    val darkSystemFill: Color = Color(0x5b787880) orContrast Color(0x70787880)
    val darkSecondarySystemFill: Color = Color(0x51787880) orContrast Color(0x66787880)
    val darkTertiarySystemFill: Color = Color(0x3d767680) orContrast Color(0x51767680)
    val darkQuaternarySystemFill: Color = Color(0x2d767680) orContrast Color(0x42767680)

    val darkPlaceholderText: Color = Color(0x4cebebf5) orContrast Color(0x8cebebf5)
    val darkSeparator: Color = Color(0x99545458) orContrast Color(0xad545458)
    val darkOpaqueSeparator: Color = Color(0xff38383a) orContrast Color(0xff38383a)

    val darkLink: Color = Color(0xff0984ff)
    val darkSystemGroupedBackground: Color = Color.Black
    val darkSecondarySystemGroupedBackground: Color = Color(0xff1c1c1e) orContrast Color(0xff242426)
    val darkTertiarySystemGroupedBackground: Color = Color(0xff2c2c2e) orContrast Color(0xff363638)

    val darkSystemBackground: Color = Color.Black
    val darkSecondarySystemBackground: Color = Color(0xff1c1c1e) orContrast Color(0xff242426)
    val darkTertiarySystemBackground: Color = Color(0xff2c2c2e) orContrast Color(0xff363638)


    private infix fun Color.orContrast(accessible: Color): Color =
        if (Accessibility.isHighContrastEnabled) accessible else this
}