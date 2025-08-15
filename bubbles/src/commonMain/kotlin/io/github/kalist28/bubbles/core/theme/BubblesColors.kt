package io.github.kalist28.bubbles.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import io.github.kalist28.bubbles.core.Accessibility
import io.github.kalist28.bubbles.core.LocalContentColor
import io.github.kalist28.bubbles.core.isHighContrastEnabled

object BubblesColors

private val isDark: Boolean
    @Composable
    @ReadOnlyComposable
    get() = BubblesTheme.colorScheme.isDark

private fun BubblesColors.default(
    dark: Boolean,
    light: Color,
) = if (dark) {
    Color(light.red, light.green + 10 / 255f, light.blue + 10 / 255f, light.alpha)
} else {
    light
}

val BubblesColors.DefaultAlpha: Color
    @Composable
    @ReadOnlyComposable
    get() = LocalContentColor.current.copy(alpha = .1f)

val BubblesColors.systemRed: Color
    @Composable
    @ReadOnlyComposable
    get() = systemRed(isDark)

val BubblesColors.systemOrange: Color
    @Composable
    @ReadOnlyComposable
    get() = systemOrange(isDark)

val BubblesColors.systemYellow: Color
    @Composable
    @ReadOnlyComposable
    get() = systemYellow(isDark)

val BubblesColors.systemGreen: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGreen(isDark)

val BubblesColors.systemBlue: Color
    @Composable
    @ReadOnlyComposable
    get() = systemBlue(isDark)

val BubblesColors.systemMint: Color
    @Composable
    @ReadOnlyComposable
    get() = systemMint(isDark)

val BubblesColors.systemTeal: Color
    @Composable
    @ReadOnlyComposable
    get() = systemTeal(isDark)

val BubblesColors.systemCyan: Color
    @Composable
    @ReadOnlyComposable
    get() = systemCyan(isDark)

val BubblesColors.systemIndigo: Color
    @Composable
    @ReadOnlyComposable
    get() = systemIndigo(isDark)

val BubblesColors.systemPurple: Color
    @Composable
    @ReadOnlyComposable
    get() = systemPurple(isDark)

val BubblesColors.systemPink: Color
    @Composable
    @ReadOnlyComposable
    get() = systemPink(isDark)

val BubblesColors.systemBrown: Color
    @Composable
    @ReadOnlyComposable
    get() = systemBrown(isDark)

val BubblesColors.systemGray: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGray(isDark)

val BubblesColors.systemGray2: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGray2(isDark)

val BubblesColors.systemGray3: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGray3(isDark)

val BubblesColors.systemGray4: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGray4(isDark)

val BubblesColors.systemGray5: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGray5(isDark)

val BubblesColors.systemGray6: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGray6(isDark)

val BubblesColors.systemGray7: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGray7(isDark)

internal val BubblesColors.systemGray8: Color
    @Composable
    @ReadOnlyComposable
    get() = systemGray8(isDark)

fun BubblesColors.systemRed(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    default(dark, Color(255, 59, 48))
} else {
    if (dark) {
        Color(255, 105, 97)
    } else {
        Color(215, 0, 21)
    }
}

fun BubblesColors.systemOrange(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    default(dark, Color(255, 149, 0))
} else {
    if (dark) {
        Color(255, 179, 64)
    } else {
        Color(201, 52, 0)
    }
}

fun BubblesColors.systemYellow(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    default(dark, Color(255, 204, 0))
} else {
    if (dark) {
        Color(255, 212, 38)
    } else {
        Color(178, 80, 0)
    }
}

fun BubblesColors.systemGreen(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(48, 209, 88)
    } else {
        Color(52, 199, 89)
    }
} else {
    if (dark) {
        Color(48, 219, 91)
    } else {
        Color(36, 138, 61)
    }
}

fun BubblesColors.systemMint(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(102, 212, 207)
    } else {
        Color(0, 199, 190)
    }
} else {
    if (dark) {
        Color(102, 212, 207)
    } else {
        Color(12, 129, 123)
    }
}

fun BubblesColors.systemTeal(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(64, 200, 244)
    } else {
        Color(48, 176, 199)
    }
} else {
    if (dark) {
        Color(93, 230, 255)
    } else {
        Color(0, 130, 153)
    }
}

fun BubblesColors.systemCyan(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(100, 210, 255)
    } else {
        Color(50, 173, 230)
    }
} else {
    if (dark) {
        Color(112, 215, 255)
    } else {
        Color(0, 113, 164)
    }
}

fun BubblesColors.systemBlue(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(10, 132, 255)
    } else {
        Color(0, 122, 255)
    }
} else {
    if (dark) {
        Color(64, 156, 255)
    } else {
        Color(0, 64, 221)
    }
}

fun BubblesColors.systemIndigo(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(94, 92, 230)
    } else {
        Color(88, 86, 214)
    }
} else {
    if (dark) {
        Color(125, 122, 255)
    } else {
        Color(54, 52, 163)
    }
}

fun BubblesColors.systemPurple(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(191, 90, 242)
    } else {
        Color(185, 82, 222)
    }
} else {
    if (dark) {
        Color(218, 143, 255)
    } else {
        Color(137, 68, 171)
    }
}

fun BubblesColors.systemPink(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    default(dark, Color(255, 45, 85))
} else {
    if (dark) {
        Color(255, 100, 130)
    } else {
        Color(211, 15, 69)
    }
}

fun BubblesColors.systemBrown(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(172, 142, 104)
    } else {
        Color(162, 132, 94)
    }
} else {
    if (dark) {
        Color(181, 148, 105)
    } else {
        Color(127, 101, 69)
    }
}

fun BubblesColors.systemGray(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    Color(142, 142, 147)
} else {
    if (dark) {
        Color(174, 174, 179)
    } else {
        Color(108, 108, 112)
    }
}

fun BubblesColors.systemGray2(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(99, 99, 102)
    } else {
        Color(174, 174, 178)
    }
} else {
    if (dark) {
        Color(124, 124, 128)
    } else {
        Color(142, 142, 147)
    }
}

fun BubblesColors.systemGray3(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(72, 72, 74)
    } else {
        Color(199, 199, 204)
    }
} else {
    if (dark) {
        Color(84, 84, 86)
    } else {
        Color(174, 174, 178)
    }
}

fun BubblesColors.systemGray4(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(58, 58, 60)
    } else {
        Color(209, 209, 214)
    }
} else {
    if (dark) {
        Color(68, 68, 70)
    } else {
        Color(188, 188, 192)
    }
}

fun BubblesColors.systemGray5(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(44, 44, 46)
    } else {
        Color(229, 229, 234)
    }
} else {
    if (dark) {
        Color(54, 54, 56)
    } else {
        Color(216, 216, 220)
    }
}

fun BubblesColors.systemGray6(
    dark: Boolean,
    highContrast: Boolean = Accessibility.isHighContrastEnabled,
) = if (!highContrast) {
    if (dark) {
        Color(28, 28, 30)
    } else {
        Color(242, 242, 247)
    }
} else {
    if (dark) {
        Color(36, 36, 38)
    } else {
        Color(235, 235, 250)
    }
}

fun BubblesColors.systemGray7(
    dark: Boolean,
) = if (dark) {
    Color(35, 35, 35)
} else {
    Color(238, 238, 238)
}

fun BubblesColors.systemGray8(
    dark: Boolean,
) = if (dark) {
    Color(90, 90, 95)
} else {
    Color(254, 254, 254)
}

val BubblesColors.Black: Color
    get() = Color.Black

val BubblesColors.Blue: Color
    get() = Color.Blue

val BubblesColors.Brown: Color
    get() = Color(0xff996633)

val BubblesColors.Cyan: Color
    get() = Color.Cyan

val BubblesColors.LightGray: Color
    get() = Color(0xffaaaaaa)

val BubblesColors.DarkGray: Color
    get() = Color(0xff555555)

val BubblesColors.Gray: Color
    get() = Color(0xff7f7f7f)

val BubblesColors.Green: Color
    get() = Color.Green

val BubblesColors.Magenta: Color
    get() = Color.Magenta

val BubblesColors.Orange: Color
    get() = Color(0xffff7f00)

val BubblesColors.Purple: Color
    get() = Color(0xff7f007f)

val BubblesColors.Red: Color
    get() = Color.Red

val BubblesColors.White: Color
    get() = Color.White

val BubblesColors.Yellow: Color
    get() = Color.Yellow
