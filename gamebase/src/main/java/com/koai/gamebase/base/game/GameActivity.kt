package com.koai.gamebase.base.game

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.koai.gamebase.base.theme.Game_baseTheme

abstract class GameActivity : ComponentActivity() {
    open val enableEdgeToEdge = true

    @SuppressLint("UnusedBoxWithConstraintsScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (enableEdgeToEdge) {
            enableEdgeToEdge()
        }
        setContent {
            Game_baseTheme {
                val gameState by remember {
                    mutableStateOf(GameState())
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Init()
                    val runtime = remember {
                        mutableLongStateOf(System.currentTimeMillis())
                    }
                    update {
                        runtime.longValue = System.currentTimeMillis()
                    }
                    GameCanvas(
                        gameState = gameState,
                        runtime = runtime.longValue,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                    ) {
                        drawGame()
                    }

                }
            }
        }
    }

    @Composable
    abstract fun Init()
    abstract fun DrawScope.drawGame()
}