package com.koai.gamebase.base.character.attr

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

data class Collider(
    var rect: Rect = Rect(Offset.Zero, Size.Zero),
    val allowOverlap: Boolean = false,
)
