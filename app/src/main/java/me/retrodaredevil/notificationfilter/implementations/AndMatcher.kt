package me.retrodaredevil.notificationfilter.implementations

import android.content.Context
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
object AndMatcherType : MatcherType {

    override val fields: Set<DataField> = setOf(DataField("matchers", DataType.LIST, ListExtra(DataType.MATCHER, 1)))
    override val name: String = "and"

    override fun getDisplayName(context: Context): String {
        return context.getString(R.string.and)
    }
    override fun getDescription(context: Context): String {
        return context.getString(R.string.and_description)
    }
    override fun getFieldName(context: Context, dataField: DataField): String {
        if(dataField.key != "matchers") throw IllegalArgumentException()

        return context.getString(R.string.field_matchers)
    }
    override fun getFieldDescription(context: Context, dataField: DataField): String {
        if(dataField.key != "matchers") throw IllegalArgumentException()

        return context.getString(R.string.field_matchers_description)
    }

    override fun createMatcherData(): MatcherData {
        return AndMatcherData()
    }
}
private class AndMatcherData : SimpleMatcherData(AndMatcherType) {
    override val isValid: Boolean
        get() = super.isValid && (this["matchers"] as List<*>?)?.all { (it as MatcherData).isValid} ?: false

    override fun createMatcher(): NotificationMatcher {
        val list = this["matchers"]!! as List<*>
        return AndMatcher(list.map { (it as MatcherData).createMatcher() })
    }

}
