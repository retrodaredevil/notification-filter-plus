package me.retrodaredevil.notificationfilter

import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class NotificationListener : NotificationListenerService() {
    /*
    May be helpful for later: https://stackoverflow.com/questions/44197274/android-notificationlistenerservice-prevent-notification-from-being-displayed
     */
    private lateinit var matcher: NotificationMatcher
//    private val matcher = OrMatcher(
//        SnapchatTypingMatcher,
//        AndMatcher(
//            SnapchatAnyGroupMatcher,
//            SnapchatSnapMatcher
//        ),
//        AndMatcher(
//            PackageNameMatcher(
//                "com.android.systemui"
//            ), TagMatcher("charging_state")
//        )
//    )
    override fun onCreate() {
        println("created notification listener!")
        val stream = assets.open("test.json")
        val gson = GsonBuilder().create()
        val json = gson.fromJson(InputStreamReader(stream), JsonObject::class.java)
        matcher = importFromJson(json).createMatcher()
    }
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if(sbn == null) return

        println("got notification: $sbn")
        if(matcher.isMatch(sbn)){
            if(sbn.isClearable) {
                cancelNotification(sbn.key)
                println("canceled notification: $sbn")
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    snoozeNotification(sbn.key, Long.MAX_VALUE)
                    println("Snoozing notification forever!")
                } else {
                    cancelNotification(sbn.key)
                    System.err.println("Cannot snooze notification. Trying to cancel.")
                }
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        if(sbn == null) return

        println("removed notification: $sbn")
    }
}