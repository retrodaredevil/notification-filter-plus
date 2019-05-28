package me.retrodaredevil.notificationfilter.match

import android.service.notification.StatusBarNotification

@FunctionalInterface
interface NotificationMatcher {
    companion object {
        operator fun invoke(f: (StatusBarNotification) -> Boolean): NotificationMatcher {
            return object : NotificationMatcher {
                override fun isMatch(sbn: StatusBarNotification): Boolean = f(sbn)
            }
        }
    }
    fun isMatch(sbn: StatusBarNotification): Boolean
}

