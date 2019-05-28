package me.retrodaredevil.notificationfilter.match

import android.service.notification.StatusBarNotification

class PackageNameMatcher(
    private val packageName: String
) : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return packageName == sbn.packageName!!
    }

}