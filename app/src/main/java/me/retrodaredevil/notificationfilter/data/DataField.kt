package me.retrodaredevil.notificationfilter.data

/**
 * Represents a field with information such as the key, type and any extra restrictions on the value.
 * This does not store the value.
 *
 * This should not be serialized
 */
data class DataField( // TODO add isOptional Boolean and implement it
    val key: String,
    val type: DataType,
    val extra: DataExtra? = null
)
