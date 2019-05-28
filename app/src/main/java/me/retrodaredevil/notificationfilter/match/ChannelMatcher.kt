package me.retrodaredevil.notificationfilter.match

import android.os.Build
import android.service.notification.StatusBarNotification
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class ChannelMatcher(
    private val channelId: String
) : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return sbn.notification.channelId == channelId
    }

}