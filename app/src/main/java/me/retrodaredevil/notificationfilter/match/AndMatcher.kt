package me.retrodaredevil.notificationfilter.match

import android.service.notification.StatusBarNotification

class AndMatcher(
    private val matchers: Collection<NotificationMatcher>
) : NotificationMatcher {
    constructor(vararg matchers: NotificationMatcher) : this(listOf(*matchers))
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return matchers.all { it.isMatch(sbn) }
    }

}