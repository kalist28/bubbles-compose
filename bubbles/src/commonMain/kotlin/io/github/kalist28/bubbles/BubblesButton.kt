@file:Suppress("unused")

package io.github.kalist28.bubbles

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.kalist28.bubbles.BubblesButtonDefaults.filledButtonColors
import io.github.kalist28.bubbles.BubblesButtonDefaults.clearButtonColors
import io.github.kalist28.bubbles.BubblesButtonTokens.BORDERED_BTN_ALPHA
import io.github.kalist28.bubbles.core.theme.BubblesTheme
import io.github.kalist28.bubbles.core.theme.Shapes
import io.github.kalist28.bubbles.core.theme.Typography

@Composable
fun BubblesButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: BubblesButtonSize = BubblesButtonSize.Regular,
    colors: BubblesButtonColors = filledButtonColors(),
    border: BorderStroke? = null,
    shape: Shape = size.shape(BubblesTheme.shapes),
    contentPadding: PaddingValues = size.contentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {

    val pressed by interactionSource.collectIsPressedAsState()

    val animatedAlpha by animateFloatAsState(
        targetValue = if (pressed) BubblesButtonTokens.PRESSED_PLAIN_BTN_ALPHA else 1f
    )

    val indication = if (colors.isPlain) null else LocalIndication.current

    BubblesSurface(
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Button },
        enabled = enabled,
        shape = shape,
        color = colors.containerColor(enabled).value,
        contentColor = colors.contentColor(enabled).value,
        border = border,
        interactionSource = interactionSource,
        indication = indication
    ) {
        ProvideTextStyle(value = size.textStyle(BubblesTheme.typography)) {
            Row(
                Modifier
                    .defaultMinSize(24.dp, 24.dp)
                    .border(1.dp, colors.outlineColor(enabled).value, shape)
                    .padding(contentPadding)
                    .graphicsLayer {
                        if (colors.isPlain && enabled) {
                            alpha = if (pressed)
                                BubblesButtonTokens.PRESSED_PLAIN_BTN_ALPHA
                            else animatedAlpha
                        }
                    },
                horizontalArrangement = Arrangement
                    .spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

@Composable
fun BubblesIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: BubblesButtonColors = clearButtonColors(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    BubblesButton(
        onClick = onClick,
        modifier = modifier
            .size(BubblesButtonTokens.IconButtonSize),
        enabled = enabled,
        colors = colors,
        size = BubblesButtonSize.Regular,
        shape = CircleShape,
        border = border,
        interactionSource = interactionSource,
        contentPadding = if (colors.isPlain)
            ZeroPadding
        else PaddingValues(8.dp),
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    )
}

@Immutable
class BubblesButtonColors internal constructor(
    private val containerColor: Color,
    private val contentColor: Color,
    private val outlineColor: Color,
    private val disabledContainerColor: Color,
    private val disabledContentColor: Color,
    private val disabledOutlineColor: Color,
    internal val isPlain: Boolean = false
) {
    /**
     * Represents the container color for this button, depending on [enabled].
     *
     * @param enabled whether the button is enabled
     */
    @Composable
    fun containerColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) containerColor else disabledContainerColor)
    }

    /**
     * Represents the content color for this button, depending on [enabled].
     *
     * @param enabled whether the button is enabled
     */
    @Composable
    fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }

    /**
     * Represents the outline color for this button, depending on [enabled].
     *
     * @param enabled whether the button is enabled
     */
    @Composable
    fun outlineColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) outlineColor else disabledOutlineColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is BubblesButtonColors) return false

        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (disabledContainerColor != other.disabledContainerColor) return false
        if (disabledContentColor != other.disabledContentColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledContainerColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }
}

enum class BubblesButtonSize(
    val shape: (Shapes) -> Shape,
    val textStyle: (Typography) -> TextStyle = { it.body },
    val contentPadding: PaddingValues
) {
    Small(
        shape = { CircleShape },
        textStyle = { it.subhead },
        contentPadding = PaddingValues(12.dp, 6.dp)
    ),
    Regular(
        shape = { it.small },
        textStyle = { it.body },
        contentPadding = PaddingValues(16.dp, 10.dp)
    ),
    Large(
        shape = { it.medium },
        textStyle = { it.body },
        contentPadding = PaddingValues(24.dp, 18.dp)
    ),
    ExtraLarge(
        shape = { CircleShape },
        textStyle = Large.textStyle,
        contentPadding = Large.contentPadding
    ),
}

@Immutable
object BubblesButtonDefaults {

    /**
     * Clear background.
     * */
    @Composable
    @ReadOnlyComposable
    fun clearButtonColors(
        contentColor: Color = BubblesTheme.colorScheme.accent,
        containerColor: Color = Color.Transparent,
        outlineColor: Color = Color.Transparent,
        disabledContentColor: Color = BubblesTheme.colorScheme.tertiaryLabel,
        disabledContainerColor: Color = Color.Transparent,
        disabledOutlineColor: Color = Color.Transparent
    ): BubblesButtonColors = BubblesButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        outlineColor = outlineColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledOutlineColor = disabledOutlineColor
    )

    /**
     * Filled background.
     * */
    @Composable
    @ReadOnlyComposable
    fun filledButtonColors(
        contentColor: Color = Color.White,
        containerColor: Color = BubblesTheme.colorScheme.accent,
        outlineColor: Color = Color.Transparent,
        disabledContentColor: Color = BubblesTheme.colorScheme.tertiaryLabel,
        disabledContainerColor: Color = BubblesTheme.colorScheme.quaternarySystemFill,
        disabledOutlineColor: Color = Color.Transparent
    ): BubblesButtonColors = BubblesButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        outlineColor = outlineColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledOutlineColor = disabledOutlineColor
    )

    /**
     * Tinted background by alpha.
     * */
    @Composable
    @ReadOnlyComposable
    fun tintedButtonColors(
        contentColor: Color = BubblesTheme.colorScheme.accent,
        containerColor: Color = contentColor.copy(alpha = BORDERED_BTN_ALPHA),
        outlineColor: Color = Color.Transparent,
        disabledContentColor: Color = BubblesTheme.colorScheme.tertiaryLabel,
        disabledContainerColor: Color = BubblesTheme.colorScheme.quaternarySystemFill,
        disabledOutlineColor: Color = Color.Transparent
    ): BubblesButtonColors = BubblesButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        outlineColor = outlineColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledOutlineColor = disabledOutlineColor
    )

    /**
     * Clear button with outlined border.
     * */
    @Composable
    @ReadOnlyComposable
    fun outlinedButtonColors(
        contentColor: Color = BubblesTheme.colorScheme.accent,
        containerColor: Color = Color.Transparent,
        outlineColor: Color = BubblesTheme.colorScheme.accent,
        disabledContentColor: Color = BubblesTheme.colorScheme.tertiaryLabel,
        disabledContainerColor: Color = Color.Transparent,
        disabledOutlineColor: Color = BubblesTheme.colorScheme.tertiaryLabel
    ): BubblesButtonColors = BubblesButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        outlineColor = outlineColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledOutlineColor = disabledOutlineColor
    )
}

internal object BubblesButtonTokens {
    const val PRESSED_PLAIN_BTN_ALPHA = .33f
    const val BORDERED_BTN_ALPHA = .2f
    val IconButtonSize = 42.dp
}

private val ZeroPadding = PaddingValues(0.dp)
