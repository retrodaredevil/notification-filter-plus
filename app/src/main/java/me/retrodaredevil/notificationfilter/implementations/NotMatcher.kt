package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.NotificationMatcher
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.*

class NotMatcher(
    private val matcher: NotificationMatcher
) : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return !matcher.isMatch(sbn)
    }

}
class NotMatcherData : SimpleMatcherData(
    NotMatcherType
) {
    override fun createMatcher(): NotificationMatcher {
        return NotMatcher((this["matcher"] as MatcherData).createMatcher())
    }
}
val NotMatcherType = SimpleMatcherType(
    setOf(
        FieldInfo(
            DataField("matcher", DataType.MATCHER),
            R.string.NULL_TODO,
            R.string.NULL_TODO
        )
    ),
    "not.matcher",
    R.string.NULL_TODO,
    R.string.NULL_TODO
) { NotMatcherData() }
