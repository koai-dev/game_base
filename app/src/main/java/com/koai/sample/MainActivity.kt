package com.koai.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import com.koai.gamebase.base.character.action.CharacterAction
import com.koai.gamebase.base.character.attr.Collider
import com.koai.gamebase.base.character.attr.InitValue
import com.koai.gamebase.base.character.attr.Sprite
import com.koai.gamebase.base.character.attr.SpriteActionWithSize
import com.koai.gamebase.base.character.attr.SpritesByAction
import com.koai.gamebase.base.game.GameActivity
import com.koai.gamebase.base.game.GameState
import com.koai.sample.ui.game.BackgroundState

class MainActivity : GameActivity() {
    private var backgroundState: BackgroundState? = null
    private var backgroundState2: BackgroundState? = null
    override fun DrawScope.drawGame() {
        backgroundState?.let { drawBackground(it) }
        backgroundState2?.let { drawBackground2(it) }
    }

    private fun DrawScope.drawBackground(
        backgroundState: BackgroundState,
    ) {
        if (backgroundState.action.isWalk()) {
            if (backgroundState.initValue.currentPositionY < size.height) {
                backgroundState.move(IntOffset(0, 1), isTurbo = true)
            } else {
                backgroundState.action.climb()
            }
        } else {
            if (backgroundState.initValue.currentPositionY > 0) {
                backgroundState.move(IntOffset(0, -1), isTurbo = true)
            } else {
                backgroundState.action.walk()
            }
        }
        backgroundState.draw(this)
    }

    private fun DrawScope.drawBackground2(
        backgroundState: BackgroundState,
    ) {
        if (backgroundState.action.isWalk()) {
            if (backgroundState.initValue.currentPositionY < size.height) {
                backgroundState.move(IntOffset(0, 1), isTurbo = false)
            } else {
                backgroundState.action.climb()
            }
        } else {
            if (backgroundState.initValue.currentPositionY > 0) {
                backgroundState.move(IntOffset(0, -1), isTurbo = false)
            } else {
                backgroundState.action.walk()
            }
        }
        backgroundState.draw(this)
    }


    @Composable
    override fun Init() {
        val bitmap =
            ImageBitmap.imageResource(R.drawable.ic_ca_em)
        val bitmap2 =
            ImageBitmap.imageResource(R.drawable.ic_ca_ran)

        val backgroundState  by remember {
            mutableStateOf(
                BackgroundState(
                    spritesByActions = listOf(
                        SpritesByAction(
                            action =
                            CharacterAction.Action.IDLE,
                            spriteActionWithSize = SpriteActionWithSize(
                                sprites = listOf(
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                ),
                                initSpriteSize = Size(300F, 300F),
                            )
                        ),
                        SpritesByAction(
                            action =
                            CharacterAction.Action.CLIMB,
                            spriteActionWithSize = SpriteActionWithSize(
                                sprites = listOf(
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                ),
                                initSpriteSize = Size(300F, 300F),
                            )
                        ),
                        SpritesByAction(
                            action =
                            CharacterAction.Action.WALK,
                            spriteActionWithSize = SpriteActionWithSize(
                                sprites = listOf(
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                ),
                                initSpriteSize = Size(300F, 300F),
                            )
                        )
                    ),
                    collider = Collider(
                        rect = Rect(
                            offset = Offset.Zero,
                            size = Size(300F, 300F)
                        ),
                        allowOverlap = false
                    ),
                    initValue = InitValue(currentPositionX = 500, currentPositionY = 500)
                )
            )
        }
        this.backgroundState = backgroundState

        val backgroundState2 by remember {
            mutableStateOf(
                BackgroundState(
                    spritesByActions = listOf(
                        SpritesByAction(
                            action =
                            CharacterAction.Action.IDLE,
                            spriteActionWithSize = SpriteActionWithSize(
                                sprites = listOf(
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                ),
                                initSpriteSize = Size(300F, 300F),
                            )
                        ),
                        SpritesByAction(
                            action =
                            CharacterAction.Action.CLIMB,
                            spriteActionWithSize = SpriteActionWithSize(
                                sprites = listOf(
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                ),
                                initSpriteSize = Size(300F, 300F),
                            )
                        ),
                        SpritesByAction(
                            action =
                            CharacterAction.Action.WALK,
                            spriteActionWithSize = SpriteActionWithSize(
                                sprites = listOf(
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                    Sprite(bitmap2),
                                    Sprite(bitmap),
                                ),
                                initSpriteSize = Size(300F, 300F),
                            )
                        )
                    ),
                    collider = Collider(
                        rect = Rect(
                            offset = Offset.Zero,
                            size = Size(300F, 300F)
                        ),
                        allowOverlap = false
                    ),
                    initValue = InitValue(currentPositionX = 500, currentPositionY = 1200)
                )
            )
        }
        this.backgroundState2 = backgroundState2
    }
}