package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.StringProvider

private class EndsWithMatcher(
    stringProvider: StringProvider,
    text: String,
    ignoreCase: Boolean
) : StringProviderMatcher(stringProvider, text, ignoreCase) {

    override fun isMatch(sbn: StatusBarNotification): Boolean {
        val providedText = stringProvider(sbn)
        return providedText?.endsWith(text, ignoreCase = ignoreCase) ?: false
    }

}

val EndsWithMatcherType = createStringProviderMatcherType(
    "ends.with.matcher",
    R.string.matcher_ends_with,
    R.string.matcher_ends_with_description
) { StringProviderMatcherData(it, ::EndsWithMatcher) }
