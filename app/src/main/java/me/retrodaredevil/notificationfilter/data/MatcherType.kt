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

    /**
     * Creates [MatcherData] with all of its fields blank and in the default state
     */
    fun createMatcherData(): MatcherData
}

class FieldInfo(
    val dataField: DataField,
    val displayName: Int,
    val description: Int
)
open class SimpleMatcherType(
    private val fieldInfoSet: Set<FieldInfo>,
    override val name: String,
    private val displayName: Int,
    private val description: Int,
    private val doCreateMatcherData: () -> MatcherData
) : MatcherType{
    override val fields: Set<DataField> = fieldInfoSet.map{ it.dataField }.toSet()
    override fun getDisplayName(context: Context): String = context.getString(displayName)
    override fun getDescription(context: Context): String = context.getString(description)
    override fun getFieldName(context: Context, dataField: DataField): String {
        return context.getString(fieldInfoSet.first { it.dataField == dataField }.displayName)
    }
    override fun getFieldDescription(context: Context, dataField: DataField): String {
        return context.getString(fieldInfoSet.first { it.dataField == dataField }.description)
    }

    override fun createMatcherData(): MatcherData = doCreateMatcherData()
}