package com.koai.gamebase.base.character.attr

data class InitValue(
    var currentPositionX: Int = 0,
    var currentPositionY: Int = 0,
    val velocity: Velocity = Velocity(),
)
