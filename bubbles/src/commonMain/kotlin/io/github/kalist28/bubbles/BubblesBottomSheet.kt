/*
 * Copyright 2025 Bubbles Compose project and open source contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kalist28.bubbles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.composables.core.BottomSheet
import com.composables.core.BottomSheetState
import io.github.kalist28.bubbles.core.LocalContainerColor
import io.github.kalist28.bubbles.core.LocalContentColor
import io.github.kalist28.bubbles.core.theme.BubblesTheme

/**
 * A styled wrapper over the unstyled [BottomSheet] that provides iOS-inspired design
 * with rounded corners, drag indication, and theme integration.
 *
 * @param state The [BottomSheetState] that controls the sheet.
 * @param modifier Modifier to be applied to the sheet.
 * @param enabled Whether the sheet is enabled.
 * @param imeAware Automatically move the sheet according to the soft keyboard's height.
 * @param content The content of the sheet.
 */
@Composable
fun BubblesBottomSheet(
    state: BottomSheetState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: BubblesBottomSheetColors = BubblesBottomSheetDefaults.colors(),
    shape: Shape = BubblesBottomSheetDefaults.shape,
    dragIndication: (@Composable ColumnScope.() -> Unit)? = {
        BubblesBottomSheetDefaults.DragIndication(
            color = colors.dragIndicationColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    top = BubblesBottomSheetTokens.DragIndicationTopPadding,
                    bottom = BubblesBottomSheetTokens.DragIndicationBottomPadding
                )
        )
    },
    imeAware: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    BottomSheet(
        state = state,
        modifier = modifier
            .fillMaxWidth()
            .background(colors.containerColor, shape),
        imeAware = imeAware,
        enabled = enabled,
        shape = shape,
        backgroundColor = colors.containerColor,
        contentColor = colors.contentColor,
        contentPadding = contentPadding,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.contentColor,
            LocalContainerColor provides colors.containerColor
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                dragIndication?.invoke(this)
                content()
            }
        }
    }
}

/**
 * Defaults for [BubblesBottomSheet].
 */
@Immutable
object BubblesBottomSheetDefaults {

    val shape: Shape
        @Composable
        @ReadOnlyComposable
        get() = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)

    @Composable
    @ReadOnlyComposable
    fun colors(
        containerColor: Color = BubblesTheme.colorScheme.secondarySystemBackground,
        contentColor: Color = BubblesTheme.colorScheme.label,
        dragIndicationColor: Color = BubblesTheme.colorScheme.separator,
    ): BubblesBottomSheetColors = BubblesBottomSheetColors(
        containerColor = containerColor,
        contentColor = contentColor,
        dragIndicationColor = dragIndicationColor,
    )

    /**
     * Drag indication handle for bottom sheet—a small rounded pill typically shown at the top center.
     */
    @Composable
    fun DragIndication(
        modifier: Modifier = Modifier,
        color: Color = BubblesTheme.colorScheme.separator,
    ) {
        Box(
            modifier = modifier
                .width(BubblesBottomSheetTokens.DragIndicationWidth)
                .height(BubblesBottomSheetTokens.DragIndicationHeight)
                .background(
                    color = color,
                    shape = RoundedCornerShape(BubblesBottomSheetTokens.DragIndicationCornerRadius)
                )
        )
    }
}


/**
 * Colors for [BubblesBottomSheet].
 */
@Immutable
class BubblesBottomSheetColors internal constructor(
    val containerColor: Color,
    val contentColor: Color,
    val dragIndicationColor: Color,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is BubblesBottomSheetColors) return false

        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (dragIndicationColor != other.dragIndicationColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + dragIndicationColor.hashCode()
        return result
    }
}

internal object BubblesBottomSheetTokens {
    val DragIndicationWidth = 36.dp
    val DragIndicationHeight = 4.dp
    val DragIndicationCornerRadius = 2.dp
    val DragIndicationTopPadding = 8.dp
    val DragIndicationBottomPadding = 4.dp
}
