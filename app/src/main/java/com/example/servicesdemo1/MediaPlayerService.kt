package com.example.servicesdemo1

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MediaPlayerService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        makeToast("${this.javaClass.name} : OnCreate")
        mediaPlayer = MediaPlayer.create(this,R.raw.audio_file)
        mediaPlayer.prepare()
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        makeToast("$startId - ${intent?.getStringExtra("file_path")}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        makeToast("${this.javaClass.name} : onDestroy")
        mediaPlayer.stop()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun makeToast(text : String){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show()
    }

}