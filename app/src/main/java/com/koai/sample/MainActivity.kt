package com.koai.sample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.koai.gamebase.base.character.action.CharacterAction
import com.koai.gamebase.base.character.attr.Collider
import com.koai.gamebase.base.character.attr.InitValue
import com.koai.gamebase.base.character.attr.Sprite
import com.koai.gamebase.base.character.attr.SpriteActionWithSize
import com.koai.gamebase.base.character.attr.SpritesByAction
import com.koai.gamebase.base.game.GameState
import com.koai.gamebase.base.game.update
import com.koai.sample.ui.game.GameCanvas
import com.koai.sample.ui.game.BackgroundState
import com.koai.gamebase.base.theme.Game_baseTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedBoxWithConstraintsScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Game_baseTheme {
                val bitmap =
                    ImageBitmap.imageResource(R.drawable.ic_ca_em)
                val bitmap2 =
                    ImageBitmap.imageResource(R.drawable.ic_ca_ran)
                val gameState = remember {
                    mutableStateOf(GameState())
                }

                val backgroundState by remember {
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
                        ))
                }

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
                        ))
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    update {
                        gameState.value = gameState.value.copy(runtime = System.currentTimeMillis())
                    }
                    BoxWithConstraints(modifier = Modifier) {
                        Box {
                            GameCanvas(
                                gameState = gameState.value,
                                backgroundState = backgroundState,
                                backgroundState2 = backgroundState2,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize(),
                            )
                        }
                    }
                }
            }
        }
    }
}