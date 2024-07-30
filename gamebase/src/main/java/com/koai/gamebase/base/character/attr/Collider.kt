package com.koai.gamebase.base.character.attr

import androidx.compose.ui.geometry.Rect

data class Collider(
    var rect: Rect,
    val allowOverlap: Boolean = false
)