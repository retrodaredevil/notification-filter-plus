package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.NotificationMatcher

class OrMatcher(
    private val matchers: Collection<NotificationMatcher>
) : NotificationMatcher {
    constructor(vararg matchers: NotificationMatcher) : this(listOf(*matchers))

    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return matchers.any { it.isMatch(sbn) }
    }

}