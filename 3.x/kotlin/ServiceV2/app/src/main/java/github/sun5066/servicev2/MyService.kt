package github.sun5066.servicev2

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

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
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        val ACTION_START = "com.example.servicetest.START"
        val ACTION_RUN = "com.example.servicetest.RUN"
        val ACTION_STOP = "com.example.servicetest.STOP"
    }

    fun serviceMessage(): String {
        return "메세지 함수 호출됨!~!"
    }
}
