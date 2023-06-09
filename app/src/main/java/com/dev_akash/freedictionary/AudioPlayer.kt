package com.dev_akash.freedictionary

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class AudioPlayer @Inject constructor() {
    private var mediaPlayer: MediaPlayer? = MediaPlayer()
    val isPlayingAudio = mutableStateOf(false)

    fun play(url: String) {
        mediaPlayer?.apply {
            resetPlayer()
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )

            setDataSource(url)
            prepareAsync()

            setOnPreparedListener {
                isPlayingAudio.value = true
                start()
            }

            setOnCompletionListener {
                resetPlayer()
            }
        }
    }

    fun clearResources() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun resetPlayer(){
        isPlayingAudio.value = false
        mediaPlayer?.reset()
    }

}