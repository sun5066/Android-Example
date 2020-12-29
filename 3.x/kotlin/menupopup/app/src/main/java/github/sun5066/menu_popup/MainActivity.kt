package github.sun5066.menu_popup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private lateinit var popup: PopupMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onPopup(_view: View) {
        popup = PopupMenu(applicationContext, _view)
        popup.menuInflater.inflate(R.menu.popup, popup.menu)

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.copy -> {
                    Toast.makeText(baseContext, "카피", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.move -> {
                    Toast.makeText(baseContext, "이동", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.share -> {
                    Toast.makeText(baseContext, "공유", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.remove -> {
                    Toast.makeText(baseContext, "삭제", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}
