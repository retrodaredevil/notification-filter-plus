package me.retrodaredevil.notificationfilter

import android.service.notification.StatusBarNotification

/**
 * A [NotificationMatcher] represents a simple object with a single function: to return true or false. It does not
 * store information on how to replicate its behavior (it cannot save itself). For that functionality, look at
 * [me.retrodaredevil.notificationfilter.data.MatcherData]
 */
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

