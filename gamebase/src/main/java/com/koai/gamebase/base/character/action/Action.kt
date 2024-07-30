package com.koai.gamebase.base.character.action

data class CharacterAction(private var status: Action = Action.IDLE) {
    enum class Action {
        IDLE, JUMP, WALK, RUN, ATTACK, CLIMB, DIE, INJURED, UP, DOWN, LEFT, RIGHT
    }

    fun run() {
        status = Action.RUN
    }

    fun jump() {
        status = Action.JUMP
    }

    fun walk() {
        status = Action.WALK
    }

    fun attack() {
        status = Action.ATTACK
    }

    fun climb() {
        status = Action.CLIMB
    }

    fun die() {
        status = Action.DIE
    }

    fun injured() {
        status = Action.INJURED
    }

    fun moveUp() {
        status = Action.UP
    }

    fun moveDown() {
        status = Action.DOWN
    }

    fun moveLeft() {
        status = Action.LEFT
    }

    fun moveRight() {
        status = Action.RIGHT
    }

    fun isRun() = status == Action.RUN
    fun isJump() = status == Action.JUMP
    fun isWalk() = status == Action.WALK
    fun isAttack() = status == Action.ATTACK
    fun isClimb() = status == Action.CLIMB
    fun isDie() = status == Action.DIE
    fun isInjured() = status == Action.INJURED
    fun isUp() = status == Action.UP
    fun isDown() = status == Action.DOWN
    fun isRight() = status == Action.RIGHT
    fun isLeft() = status == Action.LEFT

    fun current() = status
}