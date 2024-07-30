package com.koai.sample.ui.game

import com.koai.gamebase.base.character.Character
import com.koai.gamebase.base.character.attr.Collider
import com.koai.gamebase.base.character.attr.InitValue
import com.koai.gamebase.base.character.attr.SpritesByAction

data class BackgroundState(
    override val spritesByActions: List<SpritesByAction>,
    override val collider: Collider,
    override val initValue: InitValue,
) : Character()