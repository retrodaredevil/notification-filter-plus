package me.retrodaredevil.notificationfilter

import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.match.*

class NotificationListener : NotificationListenerService() {
    private val matcher = OrMatcher(
        SnapchatTypingMatcher,
        AndMatcher(SnapchatAnyGroupMatcher, SnapchatSnapMatcher),
        AndMatcher(PackageNameMatcher("com.android.systemui"), TagMatcher("charging_state"))
    )
    override fun onCreate() {
        println("created notification listener!")
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
//        if(sbn.packageName == "com.snapchat.android"){
//            if(sbn.notification.flags == 0x10){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    snoozeNotification(sbn.key, Long.MAX_VALUE)
//                } else {
//                    cancelNotification(sbn.key)
//                }
//            }
//        } else if(sbn.packageName == "com.android.systemui"){
//            if(sbn.tag == "charging_state"){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    snoozeNotification(sbn.key, Long.MAX_VALUE)
//                }
//            }
//        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        if(sbn == null) return

        println("removed notification: $sbn")
    }
}