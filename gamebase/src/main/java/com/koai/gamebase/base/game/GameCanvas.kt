package com.koai.gamebase.base.game

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope

@Composable
fun GameCanvas(
    gameState: GameState,
    runtime: Long,
    modifier: Modifier = Modifier,
    onDraw: DrawScope.(runtime: Long) -> Unit
) {
    BaseGameCanvas(gameState = gameState, modifier = modifier, runtime = runtime) {
        onDraw.invoke(this, runtime)
    }
}

@Composable
private fun BaseGameCanvas(
    gameState: GameState,
    runtime: Long,
    modifier: Modifier = Modifier,
    onDraw: DrawScope.() -> Unit
) {
    Canvas(modifier = modifier) {
        onDraw.invoke(this)
    }
}