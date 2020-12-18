package github.sun5066.activityv3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {

    private lateinit var msg: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        this.msg = intent.getStringExtra("key").toString()

        btn_goto_main.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("k", "방가방가 리턴했어요 나느 서브")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SubActivity", "서브 액티비티 실행됨")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
