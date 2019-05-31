package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.*
import me.retrodaredevil.notificationfilter.NotificationMatcher

class AndMatcher(
    private val matchers: Collection<NotificationMatcher>
) : NotificationMatcher {
    constructor(vararg matchers: NotificationMatcher) : this(listOf(*matchers))
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return matchers.all { it.isMatch(sbn) }
    }

}
val AndMatcherType = SimpleMatcherType(
    setOf(
        FieldInfo(
            DataField("matchers", DataType.LIST, ListExtra(DataType.MATCHER, 1)),
            R.string.field_matchers,
            R.string.field_matchers_description
        )
    ),
    "and",
    R.string.and,
    R.string.and_description
) { AndMatcherData() }

private class AndMatcherData : SimpleMatcherData(AndMatcherType) {
    override val isValid: Boolean
        get() = super.isValid && (this["matchers"] as List<*>?)?.all { (it as MatcherData).isValid} ?: false

    override fun createMatcher(): NotificationMatcher {
        val list = this["matchers"]!! as List<*>
        return AndMatcher(list.map { (it as MatcherData).createMatcher() })
    }

}
