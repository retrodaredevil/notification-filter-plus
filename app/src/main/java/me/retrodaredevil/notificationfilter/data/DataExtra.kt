package me.retrodaredevil.notificationfilter.data

/**
 * Represents extra constraints for a [DataField]
 */
interface DataExtra

class NumberExtra(
    val isInteger: Boolean,
    val isUnsigned: Boolean = false
) : DataExtra

class EnumExtra(
    val values: List<EnumValue>,
    val minimumSelected: Int = 1,
    val maximumSelected: Int? = null
) : DataExtra

class ListExtra(
    val type: DataType,
    val requiredSizeAtLeast: Int = 0
) : DataExtra
