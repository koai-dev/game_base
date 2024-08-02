package com.koai.gamebase.base.character.attr

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.koai.gamebase.base.character.action.CharacterAction

data class Sprite(val sprite: ImageBitmap)

data class SpriteActionWithSize(
    val sprites: List<Sprite>,
    val initSpriteSize: Size = Size(50F, 50F),
    val isFillMaxSize: Boolean = false,
)

data class SpritesByAction(
    val action: CharacterAction.Action = CharacterAction.Action.IDLE,
    val spriteActionWithSize: SpriteActionWithSize,
)

fun List<SpritesByAction>.getCurrentSpritesByAction(currentAction: CharacterAction.Action): SpritesByAction? =
    this.singleOrNull { it.action.name == currentAction.name }

@Composable
fun Int.getImageBitmap() = ImageBitmap.imageResource(this)
