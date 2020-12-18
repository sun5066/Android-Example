package github.sun5066.nofiticationexam

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun show() {
        val builder = NotificationCompat.Builder(this, "default") // 오레오 이상 버전에서 포그라운드 서비스를 사용하기 위함
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("알림 제목")
        builder.setContentText("알림 세부 텍스트")

        supportFragmentManager.beginTransaction()

        Log.d("show", "2")
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)

        val largeIcon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        builder.setLargeIcon(largeIcon)
        builder.color = Color.RED

        val ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(
            this,
            RingtoneManager.TYPE_NOTIFICATION
        )
        builder.setSound(ringtoneUri)

//        var vibrate = arrayOf(0L, 100L, 200L, 300L) as LongArray
//        builder.setVibrate(vibrate)
        builder.setAutoCancel(true)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(
                NotificationChannel(
                    "default",
                    "기본 채널",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
        manager.notify(1, builder.build())
    }

    private fun hide() {
        NotificationManagerCompat.from(this).cancel(1)
    }

    fun createNotification(view: View) {
        show()
    }

    fun removeNotification(view: View) {
        hide()
    }
}
