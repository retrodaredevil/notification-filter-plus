package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.NotificationMatcher
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.*

private class ContainsMatcher(
    private val stringProvider: StringProvider,
    private val containsText: String,
    private val ignoreCase: Boolean
) : NotificationMatcher {

    override fun isMatch(sbn: StatusBarNotification): Boolean {
        val text = stringProvider(sbn)
        if(ignoreCase){
            return text?.toLowerCase()?.contains(containsText.toLowerCase()) ?: false
        }
        return text?.contains(containsText) ?: false
    }

}
private class ContainsMatcherData : SimpleMatcherData(ContainsMatcherType){
    override fun createMatcher(): NotificationMatcher {
        return ContainsMatcher(
            (PROVIDER_TYPE_MAP[this["stringProvider"] as String] ?: error("Not valid string provider")).stringProvider,
            this["containsText"] as String,
            this["ignoreCase"] as Boolean
        )
    }

}
val ContainsMatcherType = SimpleMatcherType(
    setOf(
        FieldInfo(DataField("stringProvider", DataType.STRING_PROVIDER), R.string.NULL_TODO, R.string.NULL_TODO),
        FieldInfo(DataField("containsText", DataType.STRING), R.string.NULL_TODO, R.string.NULL_TODO),
        FieldInfo(DataField("ignoreCase", DataType.BOOLEAN), R.string.NULL_TODO, R.string.NULL_TODO)
    ),
    "contains.matcher",
    R.string.NULL_TODO,
    R.string.NULL_TODO
) { ContainsMatcherData() }
