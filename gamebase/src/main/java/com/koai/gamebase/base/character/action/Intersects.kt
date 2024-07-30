package com.koai.gamebase.base.character.action

import com.koai.gamebase.base.character.attr.Collider

fun Collider.intersects(other: Collider): Boolean {
    return if (!allowOverlap) {
        this.rect.overlaps(other.rect)
    } else false
}
