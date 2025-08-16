package io.github.kalist28.bubbles

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.composeunstyled.TextField
import com.composeunstyled.TextInput
import io.github.kalist28.bubbles.core.LocalTextStyle
import io.github.kalist28.bubbles.core.ProvideContentColor
import io.github.kalist28.bubbles.core.nullableWrapper
import io.github.kalist28.bubbles.core.theme.BubblesColors
import io.github.kalist28.bubbles.core.theme.BubblesTheme
import io.github.kalist28.bubbles.core.theme.LocalShapes
import io.github.kalist28.bubbles.core.theme.systemPaleBlue
import io.github.kalist28.bubbles.core.theme.systemRed

@Composable
fun BubblesTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    editable: Boolean = true,
    isError: Boolean = false,
    colors: BubblesTextFieldColors = BubblesTextFieldDefaults.colors(),
    cursorBrush: Brush = SolidColor(colors.cursorColor(isError).value),
    textStyle: TextStyle = LocalTextStyle.current,
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    contentPadding: PaddingValues = BubblesTextFieldDefaults.padding,
    placeholder: (@Composable () -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) = TextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    editable = editable,
    cursorBrush = cursorBrush,
    textStyle = textStyle,
    singleLine = singleLine,
    minLines = minLines,
    maxLines = maxLines,
    keyboardActions = keyboardActions,
    keyboardOptions = keyboardOptions,
    visualTransformation = visualTransformation,
    interactionSource = interactionSource,
    textColor = colors.textColor(
        enabled = editable,
        isError = isError,
        interactionSource = interactionSource
    ).value,
) {
    TextInput(
        placeholder = nullableWrapper(placeholder) { placeholder ->
            val style = LocalTextStyle.current.copy(
                color = colors.placeholderColor(
                    enabled = editable,
                    isError = isError,
                    interactionSource = interactionSource
                ).value
            )
            ProvideTextStyle(style, placeholder)
        },
        leading = nullableWrapper(leading) { leading ->
            val color = colors.leadingIconColor(
                enabled = editable,
                isError = isError,
                interactionSource = interactionSource
            ).value
            ProvideContentColor(color, leading)
        },
        trailing = nullableWrapper(trailing) { trailing ->
            val color = colors.trailingIconColor(
                enabled = editable,
                isError = isError,
                interactionSource = interactionSource
            ).value
            ProvideContentColor(color, trailing)
        },
        shape = LocalShapes.current.medium,
        backgroundColor = colors.containerColor(
            enabled = editable,
            isError = isError,
            interactionSource = interactionSource
        ).value,
        contentPadding = contentPadding
    )
}

@Composable
fun BubblesTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    editable: Boolean = true,
    isError: Boolean = false,
    colors: BubblesTextFieldColors = BubblesTextFieldDefaults.colors(),
    cursorBrush: Brush = SolidColor(colors.cursorColor(isError).value),
    textStyle: TextStyle = LocalTextStyle.current,
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    contentPadding: PaddingValues = BubblesTextFieldDefaults.padding,
    placeholder: (@Composable () -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) = BubblesTextField(
    value = value.text,
    onValueChange = { onValueChange(value.copy(text = it)) },
    textStyle = textStyle,
    modifier = modifier,
    editable = editable,
    isError = isError,
    colors = colors,
    cursorBrush = cursorBrush,
    singleLine = singleLine,
    minLines = minLines,
    maxLines = maxLines,
    keyboardActions = keyboardActions,
    keyboardOptions = keyboardOptions,
    visualTransformation = visualTransformation,
    interactionSource = interactionSource,
    contentPadding = contentPadding,
    placeholder = placeholder,
    leading = leading,
    trailing = trailing
)

@Immutable
object BubblesTextFieldDefaults {

    internal val padding = PaddingValues(16.dp)

    @Composable
    fun colors(
        focusedTextColor: Color = BubblesTheme.colorScheme.label,
        unfocusedTextColor: Color = BubblesTheme.colorScheme.label,
        disabledTextColor: Color = BubblesTheme.colorScheme.secondaryLabel,
        errorTextColor: Color = BubblesColors.systemRed,
        focusedContainerColor: Color = BubblesColors.systemPaleBlue,
        unfocusedContainerColor: Color = focusedContainerColor,
        disabledContainerColor: Color = unfocusedContainerColor.copy(.6f),
        errorContainerColor: Color = BubblesColors.systemPaleBlue
            .copy(.9f).compositeOver(BubblesColors.systemRed),
        cursorColor: Color = BubblesTheme.colorScheme.accent,
        errorCursorColor: Color = errorTextColor,
        selectionColors: TextSelectionColors =
            TextSelectionColors(cursorColor, cursorColor.copy(alpha = .25f)),
        focusedBorderColor: Color = BubblesTheme.colorScheme.quaternaryLabel,
        unfocusedBorderColor: Color = focusedBorderColor,
        disabledBorderColor: Color = focusedBorderColor,
        errorBorderColor: Color = errorTextColor,
        focusedLeadingIconColor: Color = focusedTextColor,
        unfocusedLeadingIconColor: Color = focusedLeadingIconColor,
        disabledLeadingIconColor: Color = focusedLeadingIconColor,
        errorLeadingIconColor: Color = focusedLeadingIconColor,
        focusedTrailingIconColor: Color = focusedLeadingIconColor,
        unfocusedTrailingIconColor: Color = focusedTrailingIconColor,
        disabledTrailingIconColor: Color = focusedTrailingIconColor,
        errorTrailingIconColor: Color = focusedTrailingIconColor,
        focusedPlaceholderColor: Color = BubblesTheme.colorScheme.secondaryLabel,
        unfocusedPlaceholderColor: Color = focusedPlaceholderColor,
        disabledPlaceholderColor: Color = BubblesTheme.colorScheme.tertiaryLabel,
        errorPlaceholderColor: Color = focusedPlaceholderColor,
    ): BubblesTextFieldColors =
        BubblesTextFieldColors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            disabledTextColor = disabledTextColor,
            errorTextColor = errorTextColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            disabledContainerColor = disabledContainerColor,
            errorContainerColor = errorContainerColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor,
            textSelectionColors = selectionColors,
            focusedIndicatorColor = focusedBorderColor,
            unfocusedIndicatorColor = unfocusedBorderColor,
            disabledIndicatorColor = disabledBorderColor,
            errorIndicatorColor = errorBorderColor,
            focusedLeadingIconColor = focusedLeadingIconColor,
            unfocusedLeadingIconColor = unfocusedLeadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            errorLeadingIconColor = errorLeadingIconColor,
            focusedTrailingIconColor = focusedTrailingIconColor,
            unfocusedTrailingIconColor = unfocusedTrailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            errorTrailingIconColor = errorTrailingIconColor,
            focusedPlaceholderColor = focusedPlaceholderColor,
            unfocusedPlaceholderColor = unfocusedPlaceholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor,
            errorPlaceholderColor = errorPlaceholderColor,
        )
}

@Immutable
class BubblesTextFieldColors internal constructor(
    private val focusedTextColor: Color,
    private val unfocusedTextColor: Color,
    private val disabledTextColor: Color,
    private val errorTextColor: Color,
    private val focusedContainerColor: Color,
    private val unfocusedContainerColor: Color,
    private val disabledContainerColor: Color,
    private val errorContainerColor: Color,
    private val cursorColor: Color,
    private val errorCursorColor: Color,
    private val textSelectionColors: TextSelectionColors,
    private val focusedIndicatorColor: Color,
    private val unfocusedIndicatorColor: Color,
    private val disabledIndicatorColor: Color,
    private val errorIndicatorColor: Color,
    private val focusedLeadingIconColor: Color,
    private val unfocusedLeadingIconColor: Color,
    private val disabledLeadingIconColor: Color,
    private val errorLeadingIconColor: Color,
    private val focusedTrailingIconColor: Color,
    private val unfocusedTrailingIconColor: Color,
    private val disabledTrailingIconColor: Color,
    private val errorTrailingIconColor: Color,
    private val focusedPlaceholderColor: Color,
    private val unfocusedPlaceholderColor: Color,
    private val disabledPlaceholderColor: Color,
    private val errorPlaceholderColor: Color,
) {
    /**
     * Represents the color used for the leading icon of this text field.
     *
     * @param enabled whether the text field is enabled
     * @param isError whether the text field's current value is in error
     * @param interactionSource the [InteractionSource] of this text field. Helps to determine if
     * the text field is in focus or not
     */
    @Composable
    internal fun leadingIconColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledLeadingIconColor
                isError -> errorLeadingIconColor
                focused -> focusedLeadingIconColor
                else -> unfocusedLeadingIconColor
            },
        )
    }

    /**
     * Represents the color used for the trailing icon of this text field.
     *
     * @param enabled whether the text field is enabled
     * @param isError whether the text field's current value is in error
     * @param interactionSource the [InteractionSource] of this text field. Helps to determine if
     * the text field is in focus or not
     */
    @Composable
    internal fun trailingIconColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledTrailingIconColor
                isError -> errorTrailingIconColor
                focused -> focusedTrailingIconColor
                else -> unfocusedTrailingIconColor
            },
        )
    }

    /**
     * Represents the color used for the border indicator of this text field.
     *
     * @param enabled whether the text field is enabled
     * @param isError whether the text field's current value is in error
     * @param interactionSource the [InteractionSource] of this text field. Helps to determine if
     * the text field is in focus or not
     */
    @Composable
    internal fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue =
            when {
                !enabled -> disabledIndicatorColor
                isError -> errorIndicatorColor
                focused -> focusedIndicatorColor
                else -> unfocusedIndicatorColor
            }
        return rememberUpdatedState(targetValue)
    }

    /**
     * Represents the container color for this text field.
     *
     * @param enabled whether the text field is enabled
     * @param isError whether the text field's current value is in error
     * @param interactionSource the [InteractionSource] of this text field. Helps to determine if
     * the text field is in focus or not
     */
    @Composable
    internal fun containerColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue =
            when {
                !enabled -> disabledContainerColor
                isError -> errorContainerColor
                focused -> focusedContainerColor
                else -> unfocusedContainerColor
            }
        return rememberUpdatedState(targetValue)
    }

    /**
     * Represents the color used for the placeholder of this text field.
     *
     * @param enabled whether the text field is enabled
     * @param isError whether the text field's current value is in error
     * @param interactionSource the [InteractionSource] of this text field. Helps to determine if
     * the text field is in focus or not
     */
    @Composable
    internal fun placeholderColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue =
            when {
                !enabled -> disabledPlaceholderColor
                isError -> errorPlaceholderColor
                focused -> focusedPlaceholderColor
                else -> unfocusedPlaceholderColor
            }
        return rememberUpdatedState(targetValue)
    }

    /**
     * Represents the color used for the input field of this text field.
     *
     * @param enabled whether the text field is enabled
     * @param isError whether the text field's current value is in error
     * @param interactionSource the [InteractionSource] of this text field. Helps to determine if
     * the text field is in focus or not
     */
    @Composable
    internal fun textColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue =
            when {
                !enabled -> disabledTextColor
                isError -> errorTextColor
                focused -> focusedTextColor
                else -> unfocusedTextColor
            }
        return rememberUpdatedState(targetValue)
    }

    /**
     * Represents the color used for the cursor of this text field.
     *
     * @param isError whether the text field's current value is in error
     */
    @Composable
    internal fun cursorColor(isError: Boolean): State<Color> =
        rememberUpdatedState(if (isError) errorCursorColor else cursorColor)

    /**
     * Represents the colors used for text selection in this text field.
     */
    internal val selectionColors: TextSelectionColors
        @Composable get() = textSelectionColors

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is BubblesTextFieldColors) return false

        if (focusedTextColor != other.focusedTextColor) return false
        if (unfocusedTextColor != other.unfocusedTextColor) return false
        if (disabledTextColor != other.disabledTextColor) return false
        if (errorTextColor != other.errorTextColor) return false
        if (focusedContainerColor != other.focusedContainerColor) return false
        if (unfocusedContainerColor != other.unfocusedContainerColor) return false
        if (disabledContainerColor != other.disabledContainerColor) return false
        if (errorContainerColor != other.errorContainerColor) return false
        if (cursorColor != other.cursorColor) return false
        if (errorCursorColor != other.errorCursorColor) return false
        if (textSelectionColors != other.textSelectionColors) return false
        if (focusedIndicatorColor != other.focusedIndicatorColor) return false
        if (unfocusedIndicatorColor != other.unfocusedIndicatorColor) return false
        if (disabledIndicatorColor != other.disabledIndicatorColor) return false
        if (errorIndicatorColor != other.errorIndicatorColor) return false
        if (focusedLeadingIconColor != other.focusedLeadingIconColor) return false
        if (unfocusedLeadingIconColor != other.unfocusedLeadingIconColor) return false
        if (disabledLeadingIconColor != other.disabledLeadingIconColor) return false
        if (errorLeadingIconColor != other.errorLeadingIconColor) return false
        if (focusedTrailingIconColor != other.focusedTrailingIconColor) return false
        if (unfocusedTrailingIconColor != other.unfocusedTrailingIconColor) return false
        if (disabledTrailingIconColor != other.disabledTrailingIconColor) return false
        if (errorTrailingIconColor != other.errorTrailingIconColor) return false
        if (focusedPlaceholderColor != other.focusedPlaceholderColor) return false
        if (unfocusedPlaceholderColor != other.unfocusedPlaceholderColor) return false
        if (disabledPlaceholderColor != other.disabledPlaceholderColor) return false
        if (errorPlaceholderColor != other.errorPlaceholderColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = focusedTextColor.hashCode()
        result = 31 * result + unfocusedTextColor.hashCode()
        result = 31 * result + disabledTextColor.hashCode()
        result = 31 * result + errorTextColor.hashCode()
        result = 31 * result + focusedContainerColor.hashCode()
        result = 31 * result + unfocusedContainerColor.hashCode()
        result = 31 * result + disabledContainerColor.hashCode()
        result = 31 * result + errorContainerColor.hashCode()
        result = 31 * result + cursorColor.hashCode()
        result = 31 * result + errorCursorColor.hashCode()
        result = 31 * result + textSelectionColors.hashCode()
        result = 31 * result + focusedIndicatorColor.hashCode()
        result = 31 * result + unfocusedIndicatorColor.hashCode()
        result = 31 * result + disabledIndicatorColor.hashCode()
        result = 31 * result + errorIndicatorColor.hashCode()
        result = 31 * result + focusedLeadingIconColor.hashCode()
        result = 31 * result + unfocusedLeadingIconColor.hashCode()
        result = 31 * result + disabledLeadingIconColor.hashCode()
        result = 31 * result + errorLeadingIconColor.hashCode()
        result = 31 * result + focusedTrailingIconColor.hashCode()
        result = 31 * result + unfocusedTrailingIconColor.hashCode()
        result = 31 * result + disabledTrailingIconColor.hashCode()
        result = 31 * result + errorTrailingIconColor.hashCode()
        result = 31 * result + focusedPlaceholderColor.hashCode()
        result = 31 * result + unfocusedPlaceholderColor.hashCode()
        result = 31 * result + disabledPlaceholderColor.hashCode()
        result = 31 * result + errorPlaceholderColor.hashCode()

        return result
    }
}

