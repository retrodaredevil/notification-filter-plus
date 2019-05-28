package me.retrodaredevil.notificationfilter.match

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.getContentText

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