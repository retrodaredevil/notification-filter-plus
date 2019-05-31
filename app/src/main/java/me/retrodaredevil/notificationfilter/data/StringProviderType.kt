package me.retrodaredevil.notificationfilter.data

import android.content.Context

interface StringProviderType {
    val name: String
    val stringProvider: StringProvider

    fun getDisplayName(context: Context): String
    fun getDescription(context: Context): String
}
class SimpleStringProviderType(
    override val name: String,
    override val stringProvider: StringProvider,
    private val displayName: Int,
    private val description: Int
) : StringProviderType {
    override fun getDisplayName(context: Context): String {
        return context.getString(displayName)
    }

    override fun getDescription(context: Context): String {
        return context.getString(description)
    }

}
