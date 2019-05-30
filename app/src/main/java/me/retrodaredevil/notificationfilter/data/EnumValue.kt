package me.retrodaredevil.notificationfilter.data

import android.content.Context

/**
 * Represents a possible value from [DataType.ENUM]
 */
interface EnumValue {
    /**
     * The unique name of this value
     */
    val name: String

    fun getDisplayName(context: Context): String
    fun getDescription(context: Context): String
}