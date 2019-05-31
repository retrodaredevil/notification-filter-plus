package me.retrodaredevil.notificationfilter.implementations

import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.NotificationMatcher
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.*

class PackageNameMatcher(
    private val packageName: String
) : NotificationMatcher {
    override fun isMatch(sbn: StatusBarNotification): Boolean {
        return packageName == sbn.packageName!!
    }

}
private class PackageNameMatcherData : SimpleMatcherData(PackageNameMatcherType) {
    override fun createMatcher(): NotificationMatcher {
        return PackageNameMatcher(this["packageName"] as String)
    }
}
val PackageNameMatcherType = SimpleMatcherType(
    setOf(
        FieldInfo(
            DataField("packageName", DataType.STRING),
            R.string.field_package_name,
            R.string.field_package_name_description
        )
    ),
    "package.name.matcher",
    R.string.package_name,
    R.string.package_name_description
) { PackageNameMatcherData() }
