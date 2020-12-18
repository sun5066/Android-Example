package github.sun5066.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var myService: MyService? = null
    var isService = false // onServiceDisconnected 로는 정상적으로 연결해제 되었을 때를 감지 못하기 때문에
    // 따로 연결해제되는 로직이 필요함

    val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.MyBinder
            myService = binder.getService()
            isService = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isService = false
        }
    }

    fun serviceBind(view: View) {
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun serviceUnbind(view: View) {
        if (isService) {
            unbindService(connection)
            isService = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun serviceStart(view: View) {
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.ACTION_START
    }

    fun serviceStop(view: View) {
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.ACTION_STOP
        stopService(intent)
    }

    fun callServiceFunction(view: View) {
        if (isService) {
            val msg = myService?.serviceMessage()
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "서비스가 연결되지 않았습니다!", Toast.LENGTH_LONG).show()
        }
    }
}
