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

package io.github.kalist28.bubbles.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
class Typography(
    val largeTitle: TextStyle = DefaultTextStyle.copy(
        fontSize = 34.sp,
        lineHeight = 41.sp,
    ),
    val title1: TextStyle = DefaultTextStyle.copy(
        fontSize = 28.sp,
        lineHeight = 34.sp,
    ),
    val title2: TextStyle = DefaultTextStyle.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    val title3: TextStyle = DefaultTextStyle.copy(
        fontSize = 20.sp,
        lineHeight = 25.sp,
    ),
    val headline: TextStyle = DefaultTextStyle.copy(
        fontSize = 17.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    val body: TextStyle = DefaultTextStyle.copy(
        fontSize = 17.sp,
        lineHeight = 22.sp,
    ),
    val callout: TextStyle = DefaultTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 21.sp,
    ),
    val subhead: TextStyle = DefaultTextStyle.copy(
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    val footnote: TextStyle = DefaultTextStyle.copy(
        fontSize = 13.sp,
        lineHeight = 18.sp,
    ),
    val caption1: TextStyle = DefaultTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    val caption2: TextStyle = DefaultTextStyle.copy(
        fontSize = 11.sp,
        lineHeight = 13.sp,
    ),
) {
    fun copy(
        largeTitle: TextStyle = this.largeTitle,
        title1: TextStyle = this.title1,
        title2: TextStyle = this.title2,
        title3: TextStyle = this.title3,
        headline: TextStyle = this.headline,
        body: TextStyle = this.body,
        callout: TextStyle = this.callout,
        subhead: TextStyle = this.subhead,
        footnote: TextStyle = this.footnote,
        caption1: TextStyle = this.caption1,
        caption2: TextStyle = this.caption2,
    ) = Typography(
        largeTitle = largeTitle,
        title1 = title1,
        title2 = title2,
        title3 = title3,
        headline = headline,
        body = body,
        callout = callout,
        subhead = subhead,
        footnote = footnote,
        caption1 = caption1,
        caption2 = caption2,
    )
}

val LocalTypography = staticCompositionLocalOf { Typography() }

internal val DefaultTextStyle = TextStyle.Default