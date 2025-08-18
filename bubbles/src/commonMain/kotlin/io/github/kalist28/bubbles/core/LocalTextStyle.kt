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

package io.github.kalist28.bubbles.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle

/**
 * Interoperable composition local for text style.
 *
 * It used as source of local text style in all widgets.
 *
 * You can provide your own local (for ex. basic Material local) using [LocalTextStyleProvider]
 * */
val LocalTextStyle: ProvidableCompositionLocal<TextStyle>
    @Composable
    get() = LocalTextStyleProvider.current

val LocalTextStyleProvider = staticCompositionLocalOf { EmptyLocalTextStyle }

private val EmptyLocalTextStyle = compositionLocalOf { TextStyle.Default }