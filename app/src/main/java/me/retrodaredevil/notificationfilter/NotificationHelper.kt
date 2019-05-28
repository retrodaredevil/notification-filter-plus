package me.retrodaredevil.notificationfilter

import android.app.Notification

fun Notification.getContentTitle(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_TITLE)?.toString()
}
fun Notification.getContentText(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_TEXT)?.toString()
}
fun Notification.getSubText(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_SUB_TEXT)?.toString()
}
fun Notification.getSummary(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_SUMMARY_TEXT)?.toString()
}
fun Notification.getBigText(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_BIG_TEXT)?.toString()
}
