package me.retrodaredevil.notificationfilter.data

import android.content.Context

/**
 * Represents a certain Matcher and the fields/parameters required to create it.
 */
interface MatcherType {
    val fields: Set<DataField>
    fun getField(key: String): DataField{
        return fields.first { it.key == key }
    }

    /**
     * A unique name to represent this [MatcherType]
     */
    val name: String

    fun getDisplayName(context: Context): String
    fun getDescription(context: Context): String

    fun getFieldName(context: Context, dataField: DataField): String
    fun getFieldDescription(context: Context, dataField: DataField): String

    fun createMatcherData(): MatcherData
}