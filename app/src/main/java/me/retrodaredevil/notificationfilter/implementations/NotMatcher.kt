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
            R.string.field_matcher,
            R.string.field_matcher_description
        )
    ),
    "not.matcher",
    R.string.matcher_not,
    R.string.matcher_not_description
) { NotMatcherData() }
