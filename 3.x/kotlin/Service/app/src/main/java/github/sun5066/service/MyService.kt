package github.sun5066.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.view.View

class MyService : Service() {

    inner class MyBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }

    val binder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("StartedService", "action=$action")
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        val ACTION_START = "com.example.servicetest.START"
        val ACTION_RUN = "com.example.servicetest.RUN"
        val ACTION_STOP = "com.example.servicetest.STOP"
    }

    override fun onCreate() {
        Log.d("Service", "서비스 생성됨")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d("Service", "서비스 종료됨")
        super.onDestroy()
    }

    fun serviceMessage(): String {
        return "Hello Activity! I am Service"
    }
}
