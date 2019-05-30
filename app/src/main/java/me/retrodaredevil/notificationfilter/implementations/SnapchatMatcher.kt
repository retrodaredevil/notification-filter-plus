package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.getContentText
import me.retrodaredevil.notificationfilter.getContentTitle
import me.retrodaredevil.notificationfilter.NotificationMatcher

val SnapchatMatcher = PackageNameMatcher("com.snapchat.android")

/**
 * Filters for only snaps, not texts/messages
 */
val SnapchatSnapMatcher = AndMatcher(
    SnapchatMatcher,
    ColorMatcher(-902057)
)
/**
 * Matches for only
 */
val SnapchatMessageMatcher = AndMatcher(
    SnapchatMatcher,
    ColorMatcher(-15815169)
)

val SnapchatTypingMatcher = NotificationMatcher {
    SnapchatMatcher.isMatch(it) && it.notification.getContentText()?.endsWith(
        "is typing..."
    ) ?: false
}

object SnapchatAnyGroupMatcher : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return SnapchatMatcher.isMatch(sbn) && sbn.notification.getContentTitle() != "Snapchat"
    }
}
object SnapchatAnyPrivateMessageMatcher : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        val title = sbn.notification.getContentTitle()
        println("title is: '$title'")
        return SnapchatMatcher.isMatch(sbn) && title == "Snapchat"
    }
}
