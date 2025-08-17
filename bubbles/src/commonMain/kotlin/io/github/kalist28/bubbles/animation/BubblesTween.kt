package io.github.kalist28.bubbles.animation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween

/**
 * Bubbles [tween] transition spec.
 *
 * Default values are used for iOS view transitions such as
 * UINavigationController, UIAlertController
 * */
fun <T> bubblesTween(
    durationMillis: Int = BubblesTransitionDuration,
    delayMillis: Int = 0,
    easing: Easing = BubblesEasing,
): TweenSpec<T> = tween(
    durationMillis = durationMillis,
    easing = easing,
    delayMillis = delayMillis,
)

val BubblesEasing = CubicBezierEasing(0.2833f, 0.99f, 0.31833f, 0.99f)
private const val BubblesTransitionDuration = 400
