package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.*
import me.retrodaredevil.notificationfilter.NotificationMatcher

class AllMatcher(
    private val matchers: Collection<NotificationMatcher>
) : NotificationMatcher {
    constructor(vararg matchers: NotificationMatcher) : this(listOf(*matchers))
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return matchers.all { it.isMatch(sbn) }
    }

}
val AllMatcherType = SimpleMatcherType(
    setOf(
        FieldInfo(
            DataField("matchers", DataType.LIST, ListExtra(DataType.MATCHER, null, 1)),
            R.string.field_matchers,
            R.string.field_matchers_description
        )
    ),
    "all",
    R.string.all,
    R.string.all_description
) { AllMatcherData() }

private class AllMatcherData : SimpleMatcherData(AllMatcherType) {
    override val isValid: Boolean
        get() = super.isValid && (this["matchers"] as List<*>?)?.all { (it as MatcherData).isValid} ?: false

    override fun createMatcher(): NotificationMatcher {
        val list = this["matchers"]!! as List<*>
        return AllMatcher(list.map { (it as MatcherData).createMatcher() })
    }
}
