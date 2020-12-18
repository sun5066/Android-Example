package github.sun5066.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        txt_sub_activity.text = "${intent.getIntExtra("from3", 5).toString()}" // from3 ?: 5

        btn_close_sub_activity.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("returnValue", edit_message.text.toString()) // 액티비티가 종료되었을때 전달하고싶은 데이터를 인텐트(안의 번들)에 담기
            setResult(Activity.RESULT_OK, returnIntent) // 종료 상태는 OK, 데이터는 위에 생성한 인텐트 전달
            finish() // 액티비티 종료
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "액티비티가 실행됨", Toast.LENGTH_LONG).show()
        Log.d("Life Cycle", "액티비티가 실행됨")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "액티비티가 중지됨", Toast.LENGTH_LONG).show()
        Log.d("Life Cycle", "액티비티가 중지됨")
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Toast.makeText(this, "액티비티가 생성됨", Toast.LENGTH_LONG).show()
        Log.d("Life Cycle", "액티비티가 생성됨")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "액티비티가 생성중", Toast.LENGTH_LONG).show()
        Log.d("Life Cycle", "액티비티가 생성중")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "액티비티가 일시정지됨", Toast.LENGTH_LONG).show()
        Log.d("Life Cycle", "액티비티가 일시정지됨")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "액티비티가 삭제됨", Toast.LENGTH_LONG).show()
        Log.d("Life Cycle", "액티비티가 삭제됨")
    }
}
