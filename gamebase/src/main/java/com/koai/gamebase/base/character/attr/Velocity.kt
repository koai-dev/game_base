package com.koai.gamebase.base.character.attr

data class Velocity(
    val defaultSpeed: Double = 1.0,
    val maxSpeed: Double = 3.0,
    val defaultSpeedY: Double? = null,
    val defaultSpeedX: Double? = null,
    val maxSpeedX: Double? = null,
    val maxSpeedY: Double? = null,
) {
    constructor(
        defaultSpeedX: Double? = null,
        maxSpeedX: Double? = null,
        defaultSpeedY: Double? = null,
        maxSpeedY: Double? = null,
    ) : this(
        defaultSpeedY = defaultSpeedY,
        defaultSpeedX = defaultSpeedX,
        maxSpeedX = maxSpeedX,
        maxSpeedY = maxSpeedY,
        maxSpeed = 3.0,
        defaultSpeed = 1.0,
    )
}
