package me.retrodaredevil.notificationfilter.match

import android.service.notification.StatusBarNotification

class TagMatcher(
    private val tag: String?
) : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return tag == sbn.tag
    }

}