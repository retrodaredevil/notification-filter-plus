package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.StringProvider

private class ContainsMatcher(
    stringProvider: StringProvider,
    text: String,
    ignoreCase: Boolean
) : StringProviderMatcher(stringProvider, text, ignoreCase) {

    override fun isMatch(sbn: StatusBarNotification): Boolean {
        val providedText = stringProvider(sbn)
        return providedText?.contains(text, ignoreCase = ignoreCase) ?: false
    }

}

val ContainsMatcherType = createStringProviderMatcherType(
    "contains.matcher",
    R.string.NULL_TODO,
    R.string.NULL_TODO
) { StringProviderMatcherData(it, ::ContainsMatcher) }
