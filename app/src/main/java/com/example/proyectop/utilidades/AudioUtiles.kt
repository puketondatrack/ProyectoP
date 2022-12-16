package com.example.proyectop.utilidades

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.media.MediaPlayer
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.proyectop.R
import java.io.File
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.O)
class AudioUtiles(
    private val actividad:Activity,
    private val contexto: Context,
    private val btAccion:ImageButton,
            private val btDelte:ImageButton,
            private val btPlay:ImageButton,
            private val msgInicioNotaAudio:String,
    private val msgDetieneNotaAudio:String
) {
    init {
        btAccion.setOnClickListener { grabaDetiene() }
        btPlay.setOnClickListener { reproducirAudio() }
        btDelte.setOnClickListener { eliminarAudio() }

        btDelte.isEnabled = false
        btPlay.isEnabled = false

    }

    private var mediaRecorder: MediaRecorder? = null
    private var grabando = false
    var audioFile: File = File.createTempFile("audio_", ".mp3")

    private fun grabaDetiene() {
        if (ContextCompat.checkSelfPermission(
                contexto,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permission = arrayOf(Manifest.permission.RECORD_AUDIO)
            ActivityCompat.requestPermissions(actividad, permission, 0)
        } else {
            grabando = if (!grabando) {
                RecorderInit()
                iniciarGrabar()
                true
            } else {
                detenerAudio()
                false
            }

        }
    }

    private fun RecorderInit() {
        if (audioFile.exists() && audioFile.isFile) {
            audioFile.delete()
        }
        val archivo = OtrosUtiles.getTempFile("audio_")
        audioFile = File.createTempFile(archivo, ".mp3")
        mediaRecorder = MediaRecorder()
        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.DEFAULT)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder!!.setOutputFile(audioFile)
    }

    private fun iniciarGrabar() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            Toast.makeText(contexto, msgInicioNotaAudio, Toast.LENGTH_LONG).show()
            btDelte.isEnabled = false
            btPlay.isEnabled = false
            btAccion.setImageResource(R.drawable.ic_stop)
        } catch (e: java.lang.IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun detenerAudio() {
        btDelte.isEnabled = true
        btPlay.isEnabled = true
        mediaRecorder?.stop()
        mediaRecorder?.release()
        Toast.makeText(contexto, msgDetieneNotaAudio, Toast.LENGTH_LONG).show()
        btAccion.setImageResource(R.drawable.ic_mic)
    }

    private fun reproducirAudio() {
        try {
            if (audioFile.exists() && audioFile.canRead()) {
                val mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(audioFile.path)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun eliminarAudio() {
        try {
            if (audioFile.exists()) {
                audioFile.delete()
                btDelte.isEnabled = false
                btPlay.isEnabled = false
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}