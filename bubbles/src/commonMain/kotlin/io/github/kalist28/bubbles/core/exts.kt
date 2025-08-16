package io.github.kalist28.bubbles.core

import androidx.compose.runtime.Composable

internal typealias ComposableBlock = @Composable () -> Unit


internal fun nullableWrapper(
    block: ComposableBlock?,
    wrapper: @Composable (ComposableBlock) -> Unit
) = if (block == null) null else {
    @Composable { wrapper(block) }
}