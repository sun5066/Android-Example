package github.sun5066.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(
            this,
            SubActivity::class.java
        ) // 인텐트를 생성할 때 호출할 클래스의 이름 뒤에 ::class.java 를 붙이는건 규칙임

        intent.putExtra("from1", "hello Bundle")
        intent.putExtra("from2", "2020")

        btn_goto_sub_activity.setOnClickListener {
            // startActivity(intent) startActivity 로 실행된 액티비티는 값을 돌려받을 수 없다.
            startActivityForResult(intent, 5) // 임시로 99
            // 두번째 인자는 메인 액티비티에서 서브 액티비티를 호출하는 버튼이 여러 개 있을때
            // 어떤 버튼에서 호출될 것인지를 구분하는 용도
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (resultCode) {
                -1 -> {
                    val message = data?.getStringExtra("returnValue")
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
