package me.retrodaredevil.notificationfilter.data

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.*
import java.util.Collections.unmodifiableMap

/**
 * A string provider takes a [StatusBarNotification] and gives a string. The string usually represents some part of the
 * notification. This allows for checking different parts of a notification without having a separate [MatcherType] for the
 * same thing.
 */
typealias StringProvider = (sbn: StatusBarNotification) -> String?

val PackageNameProvider: StringProvider = { it.packageName }
val TitleProvider: StringProvider = { it.notification.getTitle() }
val TextProvider: StringProvider = { it.notification.getText() }
val SubTextProvider: StringProvider = { it.notification.getSubText() }
val BigTitleProvider: StringProvider = { it.notification.getBigTitle() }
val BigTextProvider: StringProvider = { it.notification.getBigText() }

val TagProvider: StringProvider = { it.tag }

val PackageNameProviderType = SimpleStringProviderType("package.name.provider", PackageNameProvider, R.string.NULL_TODO, R.string.NULL_TODO)
val TitleProviderType = SimpleStringProviderType("title.provider", TitleProvider, R.string.NULL_TODO, R.string.NULL_TODO)
val TextProviderType = SimpleStringProviderType("text.provider", TextProvider, R.string.NULL_TODO, R.string.NULL_TODO)
val TagProviderType = SimpleStringProviderType("tag.provider", TagProvider, R.string.NULL_TODO, R.string.NULL_TODO)

val PROVIDER_TYPE_MAP: Map<String, StringProviderType> = run {
    val map = mutableMapOf<String, StringProviderType>()
    for(stringProviderType in listOf(PackageNameProviderType, TitleProviderType, TextProviderType, TagProviderType)){
        map[stringProviderType.name] = stringProviderType
    }
    unmodifiableMap(map)
}
