package com.koai.gamebase.base.character.attr

data class InitValue(
    var currentPositionX: Int,
    var currentPositionY: Int,
    val velocity: Velocity = Velocity()
)