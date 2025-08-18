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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import io.github.kalist28.bubbles.animation.bubblesTween
import io.github.kalist28.bubbles.core.LocalContentColor
import io.github.kalist28.bubbles.core.LocalTextStyle
import io.github.kalist28.bubbles.core.theme.BubblesColors
import io.github.kalist28.bubbles.core.theme.BubblesTheme
import io.github.kalist28.bubbles.core.theme.White
import io.github.kalist28.bubbles.core.theme.systemGray2

/**
 * Sliding segmented control
 *
 * @param selectedTabIndex index of the current selected tab
 * @param modifier control modifier
 * @param colors segmented control colors
 * @param shape shape of the segmented control and its indicator
 * @param paddingValues outer paddings. Default values are equal to section paddings
 * @param indicator sliding indicator
 * @param tabs segmented control tabs. Usually [ControlTab]
 *
 * @see ControlTab
 * */
@Composable
fun BubblesSegmentedControl(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    colors: BubblesSegmentedControlColors = BubblesSegmentedControlDefaults.colors(),
    shape: Shape = BubblesSegmentedControlDefaults.shape,
    paddingValues: PaddingValues = BubblesSegmentedControlDefaults.PaddingValues,
    indicator: @Composable (tabPositions: List<BubblesTabPosition>) -> Unit = @Composable { tabPositions ->
        BubblesSegmentedControlIndicator(
            selectedTabIndex = selectedTabIndex,
            tabPositions = tabPositions,
            color = colors.indicatorColor,
            shape = shape,
            separatorColor = colors.separatorColor,
        )
    },
    tabs: @Composable SegmentedControlScope.() -> Unit,
) {
    CompositionLocalProvider(
        LocalSelectedInteractionSource provides mutableStateOf(null),
    ) {
        BubblesTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier =
                modifier
                    .padding(paddingValues)
                    .heightIn(min = BubblesSegmentedControlTokens.MinHeight)
                    .clip(shape),
            containerColor = colors.containerColor,
            contentColor = colors.contentColor,
            indicator = indicator,
            tabs = { tabs(SegmentedControlScopeImpl) },
        )
    }
}

/**
 * Sliding indicator of the [BubblesSegmentedControl]
 *
 * @param selectedTabIndex index of the current selected tab
 * @param tabPositions positions of the [BubblesSegmentedControl] tabs
 * @param shape indicator shape. Should be the same as [BubblesSegmentedControl] shape.
 * @param color indicator color
 * @param separatorColor color of the divider between tabs
 * */
@Composable
fun BubblesSegmentedControlIndicator(
    selectedTabIndex: Int,
    tabPositions: List<BubblesTabPosition>,
    modifier: Modifier = Modifier,
    shape: Shape = BubblesTheme.shapes.small,
    color: Color = BubblesSegmentedControlDefaults.colors().indicatorColor,
    separatorColor: Color = BubblesTheme.colorScheme.separator,
) {
    val isSmall = isTabSelectedAndPressed()

    val animatedSize by animateFloatAsState(if (isSmall) .95f else 1f)

    Spacer(
        modifier = Modifier
            .drawBehind {
                tabPositions
                    .dropLast(1)
                    .fastForEach {
                        translate(
                            left = it.right.toPx(),
                            top = size.height * 0.2f,
                        ) {
                            drawLine(
                                color = separatorColor,
                                start = Offset.Zero,
                                end = Offset(0f, size.height * 0.6f),
                            )
                        }
                    }
            }.graphicsLayer {
                if (selectedTabIndex in tabPositions.indices) {
                    scaleX = animatedSize
                    scaleY = animatedSize

                    val selectedTabCenter = tabPositions[selectedTabIndex]
                        .let { it.left + it.width / 2 }
                        .toPx()

                    transformOrigin = TransformOrigin(
                        pivotFractionX = selectedTabCenter / size.width,
                        pivotFractionY = .5f,
                    )
                }
            }.then(modifier)
            .bubblesTabIndicatorOffset(
                tabPositions = tabPositions,
                selectedTabIndex = selectedTabIndex,
            )
            .padding(BubblesSegmentedControlTokens.IndicatorPadding)
            .shadow(
                elevation = BubblesSegmentedControlTokens.IndicatorElevation,
                shape = shape,
            )
            .fillMaxSize()
            .background(color),
    )
}

/**
 * Tab of the [BubblesSegmentedControl]
 *
 * @param onClick tab click callback
 * @param modifier tab modifier
 * @param interactionSource tab interaction source
 * @param content tab content
 * */
@Composable
fun SegmentedControlScope.ControlTab(
    onClick: () -> Unit,
    isSelected: Boolean,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    val pressed by interactionSource.collectIsPressedAsState()

    val animatedAloha by animateFloatAsState(
        if (pressed) BubblesButtonTokens.PRESSED_PLAIN_BTN_ALPHA else 1f,
    )

    val source = LocalSelectedInteractionSource.current

    LaunchedEffect(source, isSelected) {
        if (isSelected) {
            source.value = interactionSource
        }
    }

    val animatedScale by animateFloatAsState(
        if (pressed && isSelected) .9f else 1f,
    )

    Box(
        modifier = modifier
            .heightIn(min = BubblesSegmentedControlTokens.MinHeight)
            .graphicsLayer {
                alpha = animatedAloha
                scaleY = animatedScale
                scaleX = animatedScale
            }.then(
                if (isEnabled) Modifier.clickable(
                    onClick = onClick,
                    indication = null,
                    interactionSource = interactionSource,
                    role = Role.Tab,
                )
                else Modifier
            ),
        contentAlignment = Alignment.Center,
    ) {
        val textStyle = BubblesTheme.typography.caption1.copy(
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Light,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        )
        val color by animateColorAsState(
            if (isSelected) BubblesColors.White else {
                if (isEnabled) LocalContentColor.current
                else BubblesColors.systemGray2
            }
        )
        CompositionLocalProvider(
            LocalTextStyle provides textStyle,
            LocalContentColor provides color,
            content = content,
        )
    }
}

interface SegmentedControlScope

@Stable
internal object SegmentedControlScopeImpl : SegmentedControlScope

@Immutable
class BubblesSegmentedControlColors internal constructor(
    val containerColor: Color,
    val contentColor: Color,
    val indicatorColor: Color,
    val separatorColor: Color,
)

@Immutable
internal object BubblesSegmentedControlTokens {
    val MinHeight = 34.dp
    val IndicatorElevation: Dp = 4.dp
    val IndicatorPadding: Dp = 2.dp
}

@Immutable
object BubblesSegmentedControlDefaults {
    val PaddingValues: PaddingValues
        get() = PaddingValues(
            vertical = 8.dp,
            horizontal = 18.dp,
        )

    val shape: Shape
        @Composable
        @ReadOnlyComposable
        get() = BubblesTheme.shapes.medium

    @Composable
    @ReadOnlyComposable
    fun colors(
        containerColor: Color = BubblesTheme.colorScheme.quaternarySystemFill,
        indicatorColor: Color = BubblesTheme.colorScheme.accent,
        contentColor: Color = BubblesTheme.colorScheme.label,
        separatorColor: Color = BubblesTheme.colorScheme.separator,
    ) = BubblesSegmentedControlColors(
        containerColor = containerColor,
        contentColor = contentColor,
        indicatorColor = indicatorColor,
        separatorColor = separatorColor,
    )
}

private val OffsetShift = 10.dp

private fun Modifier.bubblesTabIndicatorOffset(
    tabPositions: List<BubblesTabPosition>,
    selectedTabIndex: Int,
): Modifier =
    composed(
        inspectorInfo =
            debugInspectorInfo {
                name = "tabIndicatorOffset"
                value = tabPositions[selectedTabIndex]
            },
    ) {
        val isFirst = selectedTabIndex == 0
        val isLast = selectedTabIndex == tabPositions.lastIndex

        val currentTabPosition = tabPositions[selectedTabIndex]

        val currentTabWidth by animateDpAsState(
            targetValue =
                currentTabPosition.width +
                        if (isFirst || isLast) OffsetShift / 2 else OffsetShift,
            animationSpec = bubblesTween(),
        )
        val indicatorOffset by animateDpAsState(
            targetValue = currentTabPosition.left - if (isFirst) 0.dp else OffsetShift / 2,
            animationSpec = bubblesTween(),
        )

        fillMaxWidth()
            .wrapContentSize(Alignment.CenterStart)
            .offset(x = indicatorOffset)
            .width(currentTabWidth)
    }

@Composable
private fun isTabSelectedAndPressed(): Boolean {
    val source = LocalSelectedInteractionSource.current.value ?: return false

    return source.collectIsPressedAsState().value
}

private val LocalSelectedInteractionSource =
    compositionLocalOf<MutableState<InteractionSource?>> {
        mutableStateOf(null)
    }
