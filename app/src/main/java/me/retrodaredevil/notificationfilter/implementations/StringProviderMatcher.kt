package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.NotificationMatcher
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.*

abstract class StringProviderMatcher(
    protected val stringProvider: StringProvider,
    protected val text: String,
    protected val ignoreCase: Boolean
) : NotificationMatcher

class StringProviderMatcherData(
    matcherType: MatcherType,
    private val createMatcher: (StringProvider, String, Boolean) -> NotificationMatcher
) : SimpleMatcherData(matcherType){
    override fun createMatcher(): NotificationMatcher {
        return createMatcher(
            (PROVIDER_TYPE_MAP[this["stringProvider"] as String] ?: error("Not valid string provider")).stringProvider,
            this["text"] as String,
            this["ignoreCase"] as Boolean
        )
    }

}
fun createStringProviderMatcherType(name: String, displayName: Int, description: Int, createMatcherData: (MatcherType) -> MatcherData): MatcherType {
    return object : SimpleMatcherType(
        setOf(
            FieldInfo(DataField("stringProvider", DataType.STRING_PROVIDER), R.string.NULL_TODO, R.string.NULL_TODO),
            FieldInfo(DataField("text", DataType.STRING), R.string.NULL_TODO, R.string.NULL_TODO),
            FieldInfo(DataField("ignoreCase", DataType.BOOLEAN), R.string.NULL_TODO, R.string.NULL_TODO)
        ),
        name,
        displayName,
        description,
        { throw AssertionError() }
    ) {
        override fun createMatcherData(): MatcherData {
            return createMatcherData(this)
        }
    }
}