package com.koai.gamebase.base.character.attr

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import com.koai.gamebase.base.character.action.CharacterAction

data class Sprite(val sprite: ImageBitmap)
data class SpriteActionWithSize(val sprites: List<Sprite>, val initSpriteSize: Size)
data class SpritesByAction(
    val action: CharacterAction.Action,
    val spriteActionWithSize: SpriteActionWithSize
)

fun List<SpritesByAction>.getCurrentSpritesByAction(currentAction: CharacterAction.Action): SpritesByAction? =
    this.singleOrNull { it.action.name == currentAction.name }