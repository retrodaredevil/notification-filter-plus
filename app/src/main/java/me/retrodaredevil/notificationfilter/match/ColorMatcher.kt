package me.retrodaredevil.notificationfilter.match

import android.service.notification.StatusBarNotification

class ColorMatcher(
    /**
     * The color from [android.graphics.Color]
     */
    private val color: Int
) : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        println("color is: ${sbn.notification.color}")
        return sbn.notification.color == color
    }
}