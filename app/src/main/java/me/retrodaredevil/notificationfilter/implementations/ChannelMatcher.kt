package me.retrodaredevil.notificationfilter.implementations

import android.os.Build
import android.service.notification.StatusBarNotification
import android.support.annotation.RequiresApi
import me.retrodaredevil.notificationfilter.NotificationMatcher

@RequiresApi(Build.VERSION_CODES.O)
class ChannelMatcher(
    private val channelId: String
) : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return sbn.notification.channelId == channelId
    }

}