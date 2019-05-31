package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.getText
import me.retrodaredevil.notificationfilter.getTitle
import me.retrodaredevil.notificationfilter.NotificationMatcher

val SnapchatMatcher = PackageNameMatcher("com.snapchat.android")

/**
 * Filters for only snaps, not texts/messages
 */
val SnapchatSnapMatcher = AllMatcher(
    SnapchatMatcher,
    ColorMatcher(-902057)
)
/**
 * Matches for only
 */
val SnapchatMessageMatcher = AllMatcher(
    SnapchatMatcher,
    ColorMatcher(-15815169)
)

val SnapchatTypingMatcher = NotificationMatcher {
    SnapchatMatcher.isMatch(it) && it.notification.getText()?.endsWith(
        "is typing..."
    ) ?: false
}

object SnapchatAnyGroupMatcher : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return SnapchatMatcher.isMatch(sbn) && sbn.notification.getTitle() != "Snapchat"
    }
}
object SnapchatAnyPrivateMessageMatcher : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        val title = sbn.notification.getTitle()
        println("title is: '$title'")
        return SnapchatMatcher.isMatch(sbn) && title == "Snapchat"
    }
}
