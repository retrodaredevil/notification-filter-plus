package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.NotificationMatcher

class TagMatcher(
    private val tag: String?
) : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return tag == sbn.tag
    }

}