package me.retrodaredevil.notificationfilter.data

abstract class SimpleMatcherData(
    override val matcherType: MatcherType
) : MatcherData {
    private val map: MutableMap<String, Any> = mutableMapOf()

    override fun set(key: String, value: Any) {
        val type = matcherType.getField(key).type
        val allowed = when(type){
            DataType.LIST -> value is List<*> // we won't check each item to make sure it's OK, we'll just assume it is
            DataType.BOOLEAN -> value is Boolean
            DataType.ENUM -> throw UnsupportedOperationException()
            DataType.NUMBER -> value is Number
            DataType.MATCHER -> value is MatcherData
            DataType.STRING -> value is String
        }
        if(!allowed) throw IllegalArgumentException("value: $value is not supported for type: $type of key: $key")
        map[key] = value
    }

    override fun get(key: String): Any? {
        return map[key]
    }

    override fun isFieldValid(key: String): Boolean {
        val field = matcherType.getField(key)
        val value = this[key] as List<*>? ?: return false
        return when(field.type){
            DataType.LIST -> {
                val listExtra = field.extra as ListExtra
                return listExtra.requiredSizeAtLeast <= value.size
            }
            DataType.ENUM -> throw UnsupportedOperationException()
            DataType.STRING -> true
            DataType.MATCHER -> (value as MatcherData).isValid
            DataType.BOOLEAN -> true
            DataType.NUMBER -> true
        }
    }

    override val isValid: Boolean
        get() {
            return matcherType.fields.all { map.contains(it.key) && isFieldValid(it.key)}
        }

}