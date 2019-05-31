package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.NotificationMatcher
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.*

class AnyMatcher(
    private val matchers: Collection<NotificationMatcher>
) : NotificationMatcher {
    constructor(vararg matchers: NotificationMatcher) : this(listOf(*matchers))

    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return matchers.any { it.isMatch(sbn) }
    }

}
val AnyMatcherType = SimpleMatcherType(
    setOf(
        FieldInfo(
            DataField("matchers", DataType.LIST, ListExtra(DataType.MATCHER, 1)),
            R.string.field_matchers,
            R.string.field_matchers_description
        )
    ),
    "any",
    R.string.all,
    R.string.all_description
) { AnyMatcherData() }

private class AnyMatcherData : SimpleMatcherData(AnyMatcherType) {
    override val isValid: Boolean
        get() = super.isValid && (this["matchers"] as List<*>?)?.all { (it as MatcherData).isValid} ?: false

    override fun createMatcher(): NotificationMatcher {
        val list = this["matchers"]!! as List<*>
        return AnyMatcher(list.map { (it as MatcherData).createMatcher() })
    }
}
