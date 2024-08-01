package com.koai.gamebase.utils

import android.app.Application
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import com.koai.gamebase.R

object SoundRepository {
    private var soundRepo: SoundRepo? = null

    fun init(context: Application) {
        soundRepo = SoundRepo(context)
    }

    fun loadSound(
        soundId: Int,
        soundResourceId: Int,
    ) {
        soundRepo?.loadSound(soundId, soundResourceId)
    }

    fun playSound(soundId: Int) {
        soundRepo?.playSound(soundId)
    }

    fun playBackgroundMusic(musicResourceId: Int = R.raw.background) {
        soundRepo?.playBackgroundMusic(musicResourceId)
    }

    fun stopBackgroundMusic() {
        soundRepo?.stopBackgroundMusic()
    }

    fun release() {
        soundRepo?.release()
    }
}

internal class SoundRepo(private val context: Application) {
    private val soundPool: SoundPool
    private val soundMap: HashMap<Int, Int> = HashMap()
    private var mediaPlayer: MediaPlayer? = null

    init {
        val audioAttributes =
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build()

        soundPool =
            SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build()
    }

    fun loadSound(
        soundId: Int,
        soundResourceId: Int,
    ) {
        val sound = soundPool.load(context, soundResourceId, 1)
        soundMap[soundId] = sound
    }

    fun playSound(soundId: Int) {
        soundMap[soundId]?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    fun playBackgroundMusic(musicResourceId: Int = R.raw.background) {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, musicResourceId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    fun stopBackgroundMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun release() {
        soundPool.release()
        mediaPlayer?.release()
    }
}
