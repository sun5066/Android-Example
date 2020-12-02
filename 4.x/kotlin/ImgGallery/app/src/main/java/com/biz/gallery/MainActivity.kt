package com.biz.gallery

import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.biz.gallery.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imgView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        imgView = findViewById(R.id.img_view)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            // 시스템에 내장된 Intent 호출 중에서 가져오기(PICK) 인텐트를 사용하겠다
            val imgIntent = Intent(Intent.ACTION_PICK)

            // 내장된 컨텐츠를 가져오는 기능
            imgIntent.type = MediaStore.Images.Media.CONTENT_TYPE

            // 컨텐츠의 저장된 위치 등의 정보를 달라
            imgIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            // Activity 를 호출한 후 호출된 Activity 가 종료할때
            // 어떤 값을 return 하면 그 값을 수신하겠다 라는 전제하에 사용하는 메서드
            // requestCode : 호출하는 Activity 에 대한 임의로 설정한 Key 값
            startActivityForResult(imgIntent, REQ_CODE_SELECT_IMAGE)
        }
    }

    /**
     * 호출받은 Activity 에서 setResult() 메서드가 실행되고
     * 결과값을 return 할때 호출되는 event handler
     * fab 의 클릭 이벤트에서 갤러리를 open 하고 사진을 클릭했을때
     * 그 결과를 수신한 method
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                /**
                 * `in`
                 * expecting Property 는 변수 이름을 백팃(`) 으로 감싼다
                 *
                 * 사진 갤러리 Activity 가 되돌려준 사진 정보를 inputStream 으로 변환하여
                 * expecting Property 로 변환시킨다
                 * 이를 비트맵으로 변환시킴
                 */
                val `in` = contentResolver.openInputStream(data!!.data!!)
                val imgBitMap = BitmapFactory.decodeStream(`in`)
                imgView.setImageBitmap(imgBitMap)
                `in`!!.close()
            }
        }
    }

    companion object {
        private const val REQ_CODE_SELECT_IMAGE = 1
    }

    // onCreate() 에서 setSupportActionBar 메서드가 실행될때 호출
    // 햄버거 메뉴를 생성하는 LIFE CYCLE 메서드
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // 햄버거 메뉴를 확장하고, 항목을 클릭(선택)했을때 발생하는 event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        /**
         * app 에 여러개의 화면을 구성하여 화면 전환하기 1
         * 최초로 생성된 프로젝트에는 MainActivity 가 있다.
         * MainActivity 는 안드로이드 OS가 호출하여 화면을 구성하는 용도로 사용하는 클래스
         *
         * 만약 여러개의 화면(기능)을 가진 app 을 만들려면
         * Activity 를 추가한다.
         * 그러면 AndroidManifest.xml 에 Activity 가 등록된다.
         *
         * main() 에서 생성된 Activity 를 열기 위해서
         * 1. Intent 클래스를 사용하여 intent 객체를 생성
         *      Intent(누가, 누구를)
         *      가. java 에서 누가 : MainActivity.this, 누구를 : LoginActivity.class
         *      나. kotlin 에서 누가 : this, 누구를 : LoginActivity:class.java
         * 2. startActivity(intent) 형식으로 open
         */
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_login -> {
                /**
                 * activity 를 생성한 후 amin 에서 activity 를 호출하여 화면을 불로오기
                 * 이것을 명시석 Intent 호출이라고 한다.
                 * 사용자 생성 호출
                 * */
                val loginIntent = Intent(this, LoginActivity::class.java)

                // 햄버거 -> 로그인 메뉴 선택시
                // Intent 를 호출할때 어떤 값을 전달하고 싶을때
                // putExtra 메서드를 사용하여 k, V 형식으로 전달
                loginIntent.putExtra("username", "alstjr4434@naver.com")
                startActivity(loginIntent)
                true
            }
            R.id.action_phone -> {
                val number: String = "tel:010-111-1111"

                // number 변수에 담긴 문자열을 기준으로 전화화면 띄우기
                // 암시적 Intent 호출
                // 만약 device 에 전화걸기 어플이 다수 설치되어 있을때
                // 어플에서 전화걸기 Intent 를 호출하면 Device OS 에 설정된 기본 어플을
                // 실행하여 전화걸기 화면을 보여주는것
                // 전화걸기 어플이 무엇이 있던지 상관 없이 내가만든 어플에서
                // 전화걸기를 수행할 수 있다.
                // 특정한 어플에 족송되는 것을 막아서 어플이 오류가 나는 것을 최소화 하기 위한 것
                val phoneDialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(number))
                val phoneCallButtonIntent = Intent(Intent.ACTION_CALL_BUTTON, Uri.parse(number))

                // number 변수에 담긴 문자열을 기준으로 바로 전화걸기
                val phoneCallIntent = Intent(Intent.ACTION_CALL, Uri.parse(number))
                startActivity(phoneCallButtonIntent)
                true
            }
            R.id.action_internet -> {
                val internetIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"))
                startActivity(internetIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}