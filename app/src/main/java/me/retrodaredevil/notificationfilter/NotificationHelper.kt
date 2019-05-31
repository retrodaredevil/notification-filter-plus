package me.retrodaredevil.notificationfilter

import android.app.Notification

fun Notification.getTitle(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_TITLE)?.toString()
}
fun Notification.getText(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_TEXT)?.toString()
}
fun Notification.getSubText(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_SUB_TEXT)?.toString()
}
fun Notification.getSummary(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_SUMMARY_TEXT)?.toString()
}
fun Notification.getBigTitle(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_TITLE_BIG)?.toString()
}
fun Notification.getBigText(): String?{
    return this.extras.getCharSequence(Notification.EXTRA_BIG_TEXT)?.toString()
}
