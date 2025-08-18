@file:Suppress("FunctionName")

package io.github.kalist28.bubbles.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.hypot

@Stable
fun ripple(
    bounded: Boolean = true,
    shape: Shape = CircleShape,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Black.copy(alpha = 0.16f)
): RippleIndication = RippleIndication(
    color = color,
    bounded = bounded,
    shape = shape,
    radius = radius
)

data class RippleIndication(
    val color: Color,
    val bounded: Boolean = true,
    val shape: Shape = RectangleShape,
    val radius: Dp? = null,
    val expandDurationMillis: Int = 450,
    val fadeOutDurationMillis: Int = 180
) : Indication, IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode =
        RippleNode(
            interactionSource = interactionSource,
            color = color,
            bounded = bounded,
            shape = shape,
            radius = radius,
            expandDurationMillis = expandDurationMillis,
            fadeOutDurationMillis = fadeOutDurationMillis
        )
}

private class RippleEntry {
    val progress = Animatable(0f)
    val opacity = Animatable(1f)
    var fadeJob: Job? = null
}

private class RippleNode(
    private val interactionSource: InteractionSource,
    private val color: Color,
    private val bounded: Boolean,
    private val shape: Shape,
    private val radius: Dp?,
    private val expandDurationMillis: Int,
    private val fadeOutDurationMillis: Int
) : Modifier.Node(), DrawModifierNode {

    private val ripples = mutableStateListOf<RippleEntry>()
    private var interactionsJob: Job? = null

    override fun onAttach() {
        interactionsJob = coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> onPress()
                    is PressInteraction.Release -> onEnd()
                    is PressInteraction.Cancel -> onEnd()
                }
            }
        }
    }

    override fun onDetach() {
        interactionsJob?.cancel()
        interactionsJob = null
        ripples.forEach { it.fadeJob?.cancel() }
        ripples.clear()
    }

    private fun onPress() {
        val entry = RippleEntry()
        ripples += entry
        coroutineScope.launch {
            entry.progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = expandDurationMillis,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }

    private fun onEnd() {
        val entry = ripples.lastOrNull() ?: return
        entry.fadeJob?.cancel()
        entry.fadeJob = coroutineScope.launch {
            entry.opacity.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = fadeOutDurationMillis,
                    easing = LinearEasing
                )
            )
            ripples.remove(entry)
        }
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        if (ripples.isEmpty()) return

        val drawBlock: DrawScope.() -> Unit = {
            val center = Offset(size.width / 2f, size.height / 2f)
            val targetRadiusPx = radius?.toPx() ?: computeTargetRadius(size)
            val base = color

            for (r in ripples) {
                val currentR = targetRadiusPx * r.progress.value
                val alpha = base.alpha * r.opacity.value
                if (alpha > 0f && currentR > 0f) {
                    drawCircle(
                        color = base.copy(alpha = alpha),
                        radius = currentR,
                        center = center
                    )
                }
            }
        }

        if (bounded) {
            when (val outline = shape.createOutline(size, layoutDirection, this)) {
                is Outline.Rectangle -> drawBlock()
                is Outline.Rounded -> {
                    val path = Path().apply { addRoundRect(outline.roundRect) }
                    clipPath(path) { drawBlock() }
                }

                is Outline.Generic -> clipPath(outline.path) { drawBlock() }
            }
        } else {
            drawBlock()
        }
    }

    private fun computeTargetRadius(size: Size): Float {
        return (hypot(size.width.toDouble(), size.height.toDouble()) / 2.0).toFloat()
    }
}


