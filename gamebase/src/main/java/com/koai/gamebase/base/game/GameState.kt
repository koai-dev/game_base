package com.koai.gamebase.base.game

import android.os.SystemClock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToLong

data class GameState(
    private var status: Status = Status.STOPPED,
    var runtime: Long = System.currentTimeMillis(),
) {
    enum class Status {
        RUNNING,
        PAUSED,
        STOPPED,
        GAME_OVER,
    }

    fun isRunning() = status == Status.RUNNING

    fun isPaused() = status == Status.PAUSED

    fun isStopped() = status == Status.STOPPED

    fun isGameOver() = status == Status.GAME_OVER

    fun gameOver() {
        status = Status.GAME_OVER
    }

    fun run() {
        status = Status.RUNNING
    }

    fun pause() {
        status = Status.PAUSED
    }

    fun stop() {
        status = Status.STOPPED
    }

    fun getStatusName(): String {
        return status.name
    }
}

fun update(action: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val timeElapsed1 = SystemClock.elapsedRealtime()
        val timeElapsed2 = SystemClock.elapsedRealtime()
        val frameTime = min((1000.0 / (timeElapsed2 - timeElapsed1)).roundToLong(), 5L)
        delay(max(frameTime, 5))
        action.invoke()
    }.invokeOnCompletion {
        update(action)
    }
}
