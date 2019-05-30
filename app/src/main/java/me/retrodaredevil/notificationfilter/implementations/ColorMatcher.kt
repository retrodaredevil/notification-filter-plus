package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.NotificationMatcher

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