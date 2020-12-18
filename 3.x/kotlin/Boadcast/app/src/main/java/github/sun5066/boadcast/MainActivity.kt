package github.sun5066.boadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val br = MyReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter(Intent.ACTION_LOCALE_CHANGED)
        registerReceiver(br, filter)

        thread(start = true) {
            Thread.sleep(3000)
            runOnUiThread {
                showProgress(false)
            }
        }
    }

    override fun onDestroy() {
        unregisterReceiver(br)
        super.onDestroy()
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            prg_layout.visibility = View.VISIBLE
        } else {
            prg_layout.visibility = View.GONE
        }
    }
}
