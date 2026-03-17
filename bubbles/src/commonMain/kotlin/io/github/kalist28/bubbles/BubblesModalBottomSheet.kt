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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import com.composables.core.ModalBottomSheet
import com.composables.core.ModalBottomSheetState
import io.github.kalist28.bubbles.core.LocalContentColor
import io.github.kalist28.bubbles.core.LocalContainerColor
import io.github.kalist28.bubbles.core.theme.BubblesTheme

/**
 * A styled wrapper over the unstyled [ModalBottomSheet] that provides iOS-inspired design
 * with rounded corners, drag indication, and theme integration.
 *
 * This is a modal variant that focuses user attention on the sheet content.
 */
@Composable
fun BubblesModalBottomSheet(
    state: ModalBottomSheetState,
    colors: BubblesModalBottomSheetColors = BubblesModalBottomSheetDefaults.colors(),
    shape: Shape = BubblesModalBottomSheetDefaults.shape,
    showDragIndication: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        state = state,
        scrimColor = colors.scrimColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(colors.containerColor, shape)
        ) {
            CompositionLocalProvider(
                LocalContentColor provides colors.contentColor,
                LocalContainerColor provides colors.containerColor
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (showDragIndication) {
                        BubblesModalDragIndication(
                            color = colors.dragIndicationColor,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    top = BubblesModalBottomSheetTokens.DragIndicationTopPadding,
                                    bottom = BubblesModalBottomSheetTokens.DragIndicationBottomPadding
                                )
                        )
                    }
                    content()
                }
            }
        }
    }
}

/**
 * Drag indication handle for modal bottom sheet—a small rounded pill typically shown at the top center.
 */
@Composable
fun BubblesModalDragIndication(
    modifier: Modifier = Modifier,
    color: Color = BubblesTheme.colorScheme.separator,
) {
    Box(
        modifier = modifier
            .width(BubblesModalBottomSheetTokens.DragIndicationWidth)
            .height(BubblesModalBottomSheetTokens.DragIndicationHeight)
            .background(
                color = color,
                shape = RoundedCornerShape(BubblesModalBottomSheetTokens.DragIndicationCornerRadius)
            )
    )
}

/**
 * Colors for [BubblesModalBottomSheet].
 */
@Immutable
class BubblesModalBottomSheetColors internal constructor(
    val containerColor: Color,
    val contentColor: Color,
    val scrimColor: Color,
    val dragIndicationColor: Color,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is BubblesModalBottomSheetColors) return false

        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (scrimColor != other.scrimColor) return false
        if (dragIndicationColor != other.dragIndicationColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + scrimColor.hashCode()
        result = 31 * result + dragIndicationColor.hashCode()
        return result
    }
}

/**
 * Defaults for [BubblesModalBottomSheet].
 */
@Immutable
object BubblesModalBottomSheetDefaults {

    val shape: Shape
        @Composable
        @ReadOnlyComposable
        get() = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)

    @Composable
    @ReadOnlyComposable
    fun colors(
        containerColor: Color = BubblesTheme.colorScheme.secondarySystemBackground,
        contentColor: Color = BubblesTheme.colorScheme.label,
        scrimColor: Color = Color.Black.copy(alpha = 0.32f),
        dragIndicationColor: Color = BubblesTheme.colorScheme.separator,
    ): BubblesModalBottomSheetColors = BubblesModalBottomSheetColors(
        containerColor = containerColor,
        contentColor = contentColor,
        scrimColor = scrimColor,
        dragIndicationColor = dragIndicationColor,
    )
}

internal object BubblesModalBottomSheetTokens {
    val DragIndicationWidth = 36.dp
    val DragIndicationHeight = 4.dp
    val DragIndicationCornerRadius = 2.dp
    val DragIndicationTopPadding = 8.dp
    val DragIndicationBottomPadding = 4.dp
}
