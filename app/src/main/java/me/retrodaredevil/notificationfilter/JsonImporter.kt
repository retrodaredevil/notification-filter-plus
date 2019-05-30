package me.retrodaredevil.notificationfilter

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import me.retrodaredevil.notificationfilter.data.MatcherData
import me.retrodaredevil.notificationfilter.implementations.AndMatcherType
import me.retrodaredevil.notificationfilter.implementations.PackageNameMatcherType

private val BUILT_IN_MATCHERS = listOf(AndMatcherType, PackageNameMatcherType)

fun getBuiltInMatcherData(matcherTypeName: String): MatcherData? {
    val matcherType = BUILT_IN_MATCHERS.firstOrNull { it.name == matcherTypeName } ?: return null
    return matcherType.createMatcherData()
}

fun importFromJson(jsonMatcherObject: JsonObject): MatcherData {
    val matcherTypeName = jsonMatcherObject.getAsJsonPrimitive("matcherType").asString ?: throw IllegalArgumentException("$jsonMatcherObject doesn't have matcherType string!")
    val matcherData = getBuiltInMatcherData(matcherTypeName) ?: throw UnsupportedOperationException("Unsupported matcher type: $matcherTypeName")
    val fields = jsonMatcherObject.getAsJsonObject("fields")
    for(entry in fields.entrySet()){
        matcherData[entry.key] = getValueFromRaw(entry.value)
    }
    return matcherData
}
fun getValueFromRaw(value: JsonElement): Any{
    when {
        value.isJsonArray -> {
            val array = value.asJsonArray
            return array.asIterable().map { getValueFromRaw(it!!) }
        }
        value.isJsonObject -> return importFromJson(value.asJsonObject)
        value.isJsonNull -> throw UnsupportedOperationException("null not supported yet!")
        value.isJsonPrimitive -> {
            val prim = value.asJsonPrimitive
            when {
                prim.isBoolean -> return prim.asBoolean
                prim.isNumber -> return prim.asNumber
                prim.isString -> return prim.asString
            }
            throw UnsupportedOperationException("Unknown primitive value: $value")
        }
        else -> throw UnsupportedOperationException("Unknown value: $value")
    }
}
