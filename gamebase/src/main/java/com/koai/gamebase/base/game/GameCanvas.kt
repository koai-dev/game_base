package com.koai.gamebase.base.game

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope

@Composable
fun GameCanvas(
    gameState: GameState,
    modifier: Modifier = Modifier,
    onDraw: DrawScope.(runtime: Long) -> Unit
) {
    BaseGameCanvas(gameState = gameState, modifier = modifier) {
        onDraw.invoke(this, gameState.runtime)
    }
}

@Composable
private fun BaseGameCanvas(
    gameState: GameState,
    modifier: Modifier = Modifier,
    onDraw: DrawScope.() -> Unit
) {
    Canvas(modifier = modifier) {
        onDraw.invoke(this)
    }
}