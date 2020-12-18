package github.sun5066.contentresolver

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import github.sun5066.contentresolver.adapter.MusicRecyclerAdapter
import github.sun5066.contentresolver.model.MusicVO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    private fun startProcess() {
        setContentView(R.layout.activity_main)
        Toast.makeText(
            this,
            "앱이 실행되었습니다.",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun stopProcess() {
        val adapter = MusicRecyclerAdapter()
        adapter.musicList.addAll(getMusicList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                permissions[0]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions()
        } else {
            startProcess()
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, permissions, 99)
    }

    private fun getMusicList(): List<MusicVO> {
        val listUrl = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )

        val cursor = contentResolver.query(listUrl, proj, null, null, null)
        val musicList = mutableListOf<MusicVO>()
        while (cursor?.moveToNext() == true) {
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val artists = cursor.getString(2)
            val albumId = cursor.getString(3)
            val duration = cursor.getLong(4)

            val musicVO = MusicVO(id, title, artists, albumId, duration)
            musicList.add(musicVO)
        }
        return musicList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.checkPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 99) {
            var check = true
            for (grant in grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    check = false
                    break
                }
            }
            if (!check) {
                Toast.makeText(
                    this,
                    "권한 요청을 모두 승인해야지만 앱을 실행할 수 있습니다.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                startProcess()
            }
        }
    }
}
