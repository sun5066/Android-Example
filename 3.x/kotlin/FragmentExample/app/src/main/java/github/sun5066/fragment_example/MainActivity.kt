package github.sun5066.fragment_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_1.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = Fragment1()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
        }

        btn_2.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = Fragment2()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
        }

        btn_3.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = Fragment3()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
        }

        btn_4.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = Fragment4()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
        }
    }
}
