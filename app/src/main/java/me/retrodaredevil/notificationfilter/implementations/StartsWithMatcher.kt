package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.StringProvider

private class StartsWithMatcher(
    stringProvider: StringProvider,
    text: String,
    ignoreCase: Boolean
) : StringProviderMatcher(stringProvider, text, ignoreCase) {

    override fun isMatch(sbn: StatusBarNotification): Boolean {
        val providedText = stringProvider(sbn)
        return providedText?.startsWith(text, ignoreCase = ignoreCase) ?: false
    }

}

val StartsWithMatcherType = createStringProviderMatcherType(
    "starts.with.matcher",
    R.string.matcher_starts_with,
    R.string.matcher_starts_with_description
) { StringProviderMatcherData(it, ::StartsWithMatcher) }
