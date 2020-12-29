package github.sun5066.eventexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var mView1: TextView
    private lateinit var mView2: View
    private lateinit var mEdit1: EditText
    private lateinit var mEdit2: EditText
    private lateinit var mEventInbfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mView1 = findViewById(R.id.view1)
        mView2 = findViewById(R.id.view2)
        mEdit1 = findViewById(R.id.edit1)
        mEdit2 = findViewById(R.id.edit2)
        mEventInbfoTextView = findViewById(R.id.event_info_text)

        setClickEvent()
        setFocusEvent()
        setKeyEvent()
        setTouchEvent()
    }

    private fun setTouchEvent() {
        mView2.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Toast.makeText(this, "터치 다운", Toast.LENGTH_SHORT).show()
                }
                MotionEvent.ACTION_MOVE -> {
                    mEventInbfoTextView.setText("터치정보 ${event.toString()}")
                }
                MotionEvent.ACTION_UP -> {
                    Toast.makeText(this, "터치 업!", Toast.LENGTH_SHORT).show()
                }
                else -> false
            }
            true
        }
    }

    private fun setKeyEvent() {
        val keyListener = View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                Toast.makeText(this, "뒤로가기!", Toast.LENGTH_SHORT).show()
            }
            true
        }

        val keyListener2 = View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                Toast.makeText(this, "뒤로가기!", Toast.LENGTH_SHORT).show()
            }
            false
        }

        mEdit1.setOnKeyListener(keyListener)
        mEdit2.setOnKeyListener(keyListener2)
    }

    private fun setFocusEvent() {
        val focusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundColor(Color.RED)
            else v.setBackgroundColor(Color.WHITE)
        }

        mEdit1.onFocusChangeListener = focusChangeListener
        mEdit2.onFocusChangeListener = focusChangeListener
    }

    private fun setClickEvent() {
        mView1.setOnClickListener {
            Toast.makeText(this, "클릭", Toast.LENGTH_SHORT).show()
        }

        mView1.setOnLongClickListener { view ->
            Toast.makeText(this, "클릭", Toast.LENGTH_SHORT).show()
            true
        }
    }
}
