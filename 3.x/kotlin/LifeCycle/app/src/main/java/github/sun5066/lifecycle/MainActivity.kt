package github.sun5066.lifecycle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val STATE_SCORE = "playerScore"
    val STATE_LEVEL = "playerLevel"

    private lateinit var mLevelText: TextView
    private lateinit var mScoreText: TextView
    private var mLevel: Int = 0
    private var mScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLevelText = findViewById(R.id.level_text)
        mScoreText = findViewById(R.id.score_text)

//        if (savedInstanceState != null) {
//            mLevel = savedInstanceState.getInt(STATE_LEVEL)
//            mScore = savedInstanceState.getInt(STATE_SCORE)
//            mLevelText.text = "레벨 : $mLevel"
//            mScoreText.text = "레벨 : $mScore"
//        }
    }

    fun onLevelvUp(view: View) {
        mLevel++
        mLevelText.text = "레벨 : $mLevel"
    }

    fun onScoreUp(view: View) {
        mScore++
        mScoreText.text = "레벨 : $mScore"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SCORE, mScore)
        outState.putInt(STATE_LEVEL, mLevel)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mLevel = savedInstanceState.getInt(STATE_LEVEL)
        mScore = savedInstanceState.getInt(STATE_SCORE)
        mLevelText.text = "레벨 : $mLevel"
        mScoreText.text = "레벨 : $mScore"
    }

    override fun onStart() {
        super.onStart()
        Log.d("on", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("on", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("on", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("on", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("on", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("on", "onDestroy")
    }
}
