package me.retrodaredevil.notificationfilter.data

enum class DataType {
    MATCHER,
    STRING,
    BOOLEAN,
    NUMBER,
    /**
     * The type of the list is defined in [DataField.extra]. The value in [DataField.extra] will be a [ListExtra]
     */
    LIST,
    /**
     *The choices are in [DataField.extra].
     */
    ENUM,
}