package com.koai.gamebase.base.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.koai.gamebase.base.theme.Game_baseTheme

abstract class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (enableEdgeToEdge) {
            enableEdgeToEdge()
        }
        setContent {
            Game_baseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    update {

                    }
                    GameCanvas(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                    ){

                    }
                }
            }
        }
    }

    open val enableEdgeToEdge = true

    fun DrawScope.drawScope(){

    }
}