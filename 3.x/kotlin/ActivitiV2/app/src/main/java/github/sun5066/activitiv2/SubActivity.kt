package github.sun5066.activitiv2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        txt_view_sub.text = intent.getStringExtra("key") ?: "null!!!!!!!"

        btn_goto_main.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("returnKey", txt_view_sub.text.toString())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
    
    override fun onResume() {
        super.onResume()
        Toast.makeText(baseContext, "서브 액티비티가 실행중입니다...", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SubActivity", "액티비티 종료됨!")
    }
}
