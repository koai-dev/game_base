package com.koai.sample.ui.game

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import com.koai.gamebase.base.character.action.intersects
import com.koai.gamebase.base.game.GameState

@Composable
fun GameCanvas(
    gameState: GameState,
    backgroundState: BackgroundState,
    modifier: Modifier = Modifier,
    backgroundState2: BackgroundState
) {
    val context = LocalContext.current
    Canvas(modifier = modifier) {
        drawBackground(backgroundState)
        drawBackground2(backgroundState2)
        if (backgroundState.collider.intersects(backgroundState2.collider)){
            Toast.makeText(context, "hasIntersects", Toast.LENGTH_SHORT).show()
        }
    }
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