package github.sun5066.test1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferences = this.getPreferences(0)

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                login.isEnabled = s!!.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        username.setText(preferences.getString("username", "없음"))
        password.setText(preferences.getString("password", ""))

        password.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (username.text.toString().isEmpty()) false
                login()
                true
            } else false
        }

        login.setOnClickListener {
            login()
        }
    }

    fun login() {
        if (username.text.toString() != "alstjr4434@naver.com") {
            return
        }
        if (password.text.toString() != "1234") {
            return
        }

        val intent = Intent(this, SubActivity::class.java)
        intent.putExtra("username", username.text.toString())
        intent.putExtra("password", password.text.toString())

        val editor = preferences.edit()
        editor.putString("username", username.text.toString()).apply()
        editor.putString("password", password.text.toString()).apply()
        startActivityForResult(intent, 200)
    }
}
