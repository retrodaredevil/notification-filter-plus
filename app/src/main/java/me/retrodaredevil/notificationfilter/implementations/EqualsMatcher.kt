package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.StringProvider

private class EqualsMatcher(
    stringProvider: StringProvider,
    text: String,
    ignoreCase: Boolean
) : StringProviderMatcher(stringProvider, text, ignoreCase) {

    override fun isMatch(sbn: StatusBarNotification): Boolean {
        val providedText = stringProvider(sbn)
        return providedText?.equals(text, ignoreCase = ignoreCase) ?: false
    }

}

val EqualsMatcherType = createStringProviderMatcherType(
    "equals.matcher",
    R.string.NULL_TODO,
    R.string.NULL_TODO
) { StringProviderMatcherData(it, ::EqualsMatcher) }
