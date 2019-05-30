package me.retrodaredevil.notificationfilter.implementations

import android.content.Context
import android.service.notification.StatusBarNotification
import me.retrodaredevil.notificationfilter.R
import me.retrodaredevil.notificationfilter.data.*
import me.retrodaredevil.notificationfilter.NotificationMatcher

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
object PackageNameMatcherType : MatcherType {
    override val fields: Set<DataField> = setOf(DataField("packageName", DataType.STRING))
    override val name: String = "package.name.matcher"

    override fun getDisplayName(context: Context): String {
        return context.getString(R.string.package_name)
    }

    override fun getDescription(context: Context): String {
        return context.getString(R.string.package_name_description)
    }

    override fun getFieldName(context: Context, dataField: DataField): String {
        return context.getString(R.string.field_package_name)
    }

    override fun getFieldDescription(context: Context, dataField: DataField): String {
        return context.getString(R.string.field_package_name_description)
    }

    override fun createMatcherData(): MatcherData {
        return PackageNameMatcherData()
    }

}
