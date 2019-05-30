package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.getContentText
import me.retrodaredevil.notificationfilter.NotificationMatcher

class ContentTextContainsMatcher(
    private val containsText: String,
    private val ignoreCase: Boolean = false
) : NotificationMatcher {

    override fun isMatch(sbn: StatusBarNotification): Boolean {
        val contentText = sbn.notification.getContentText()
        if(ignoreCase){
            return contentText?.toLowerCase()?.contains(containsText.toLowerCase()) ?: false
        }
        return contentText?.contains(containsText) ?: false
    }

}