package me.retrodaredevil.notificationfilter

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View

private const val TEST_CHANNEL = "test_channel"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getManager().createNotificationChannel(
                NotificationChannel(TEST_CHANNEL, getString(R.string.notification_test_channel), NotificationManager.IMPORTANCE_DEFAULT)
            )
        }
    }
    fun onOpenAccessSettings(view: View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        } else {
            startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
        }
    }
    fun onSendTestNotification(view: View){
        val style = Notification.BigTextStyle()
            .bigText(getString(R.string.test_notification_big_text))
            .setBigContentTitle(getString(R.string.test_notification_big_test_content_title))
            .setSummaryText(getString(R.string.test_notification_big_test_summary))

        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(
            1,
            getBuilder()
                .setSmallIcon(android.R.drawable.ic_menu_day)
                .setContentTitle(getString(R.string.test_notification_content_title))
                .setContentText(getString(R.string.test_notification_content_text))
                .setSubText(getString(R.string.test_notification_sub_text))
                .setStyle(style)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setColor(Color.RED)
                .build()
        )
    }
    fun onOpenEditMatcherData(view: View){
        val intent = Intent(this, EditMatcherData::class.java)
        intent.putExtra(EditMatcherData.JSON_DATA, "TODO json data")
        startActivity(intent)
    }
    private fun getBuilder(): Notification.Builder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Notification.Builder(this, TEST_CHANNEL)
        }
        return Notification.Builder(this)
    }
    private fun getManager() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}
