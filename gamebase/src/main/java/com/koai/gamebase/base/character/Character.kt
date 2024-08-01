package com.koai.gamebase.base.character

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toOffset
import androidx.compose.ui.unit.toSize
import com.koai.gamebase.base.character.action.CharacterAction
import com.koai.gamebase.base.character.attr.Collider
import com.koai.gamebase.base.character.attr.InitValue
import com.koai.gamebase.base.character.attr.Sprite
import com.koai.gamebase.base.character.attr.SpriteActionWithSize
import com.koai.gamebase.base.character.attr.SpritesByAction
import com.koai.gamebase.base.character.attr.getCurrentSpritesByAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class Character {
    val action = CharacterAction()
    abstract val spritesByActions: List<SpritesByAction>
    open val collider: Collider = Collider()
    open val initValue: InitValue = InitValue()
    open val delayTimeLoadSprite: Long = 200

    private fun currentSpritesWithSize(): SpriteActionWithSize? =
        spritesByActions.getCurrentSpritesByAction(action.current())?.spriteActionWithSize

    private fun currentSprites(): List<Sprite>? = currentSpritesWithSize()?.sprites

    private var currentIndexSprite = 0
    private var lastIndexSprite = -1

    fun move(
        offset: IntOffset,
        isTurbo: Boolean = false,
    ) {
        initValue.currentPositionX += (offset.x * if (isTurbo) initValue.velocity.maxSpeed else initValue.velocity.defaultSpeed).toInt()
        initValue.currentPositionY += (offset.y * if (isTurbo) initValue.velocity.maxSpeed else initValue.velocity.defaultSpeed).toInt()
    }

    fun draw(
        drawScope: DrawScope,
        onDraw: ((Collider) -> Unit)? = null,
    ): Collider {
        drawScope.apply {
            val dstOffset =
                IntOffset(
                    x = initValue.currentPositionX,
                    y = initValue.currentPositionY,
                )

            val dstSize =
                IntSize(
                    width =
                        (
                            currentSpritesWithSize()?.initSpriteSize?.width
                                ?: 0F
                        ).toInt(),
                    height =
                        (
                            currentSpritesWithSize()?.initSpriteSize?.width
                                ?: 0F
                        ).toInt(),
                )
            if (0 < initValue.currentPositionY && initValue.currentPositionY < size.height &&
                0 < initValue.currentPositionX && initValue.currentPositionX < size.width
            ) {
                collider.rect = Rect(offset = dstOffset.toOffset(), size = dstSize.toSize())
                drawCharacter(dstOffset, dstSize, onDraw)
            }
        }
        return collider
    }

    private fun DrawScope.drawCharacter(
        dstOffset: IntOffset,
        dstSize: IntSize,
        onDraw: ((Collider) -> Unit)?,
    ) {
        currentSprites()?.get(currentIndexSprite)?.sprite?.let {
            drawImage(
                image = it,
                dstOffset = dstOffset,
                dstSize = dstSize,
            )
            if (lastIndexSprite != currentIndexSprite) {
                lastIndexSprite = currentIndexSprite
                nextSprite()
            }
            onDraw?.invoke(collider)
        }
    }

    private fun nextSprite() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(delayTimeLoadSprite)
        }.invokeOnCompletion {
            if (currentIndexSprite < (currentSprites()?.size ?: 0) - 1) {
                currentIndexSprite++
            } else {
                currentIndexSprite = 0
            }
        }
    }
}
