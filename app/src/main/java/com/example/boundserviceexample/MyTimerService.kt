package com.example.boundserviceexample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*


class MyTimerService : Service() {
    private val emranBinder: IBinder = MyLocalBinder()
    override fun onBind(intent: Intent): IBinder {
        return emranBinder
    }

    fun getCurrentTime() : String {
        var df:SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
        return df.format(Date())
    }

    class MyLocalBinder : Binder() {
        fun getService() : MyTimerService {
            return MyTimerService()
        }
    }
}
