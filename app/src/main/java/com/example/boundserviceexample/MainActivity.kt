package com.example.boundserviceexample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var timerService: MyTimerService
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val i = Intent(this, MyTimerService::class.java)
        bindService(i, theConnection, Context.BIND_AUTO_CREATE)
    }

    fun btnShowTime(view: View) {
        val currentTime = timerService.getCurrentTime()
        val tvShowTime:TextView = findViewById<TextView>(R.id.tvShowTime)
        tvShowTime.text = currentTime
    }

    private var theConnection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyTimerService.MyLocalBinder
            timerService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }
}
