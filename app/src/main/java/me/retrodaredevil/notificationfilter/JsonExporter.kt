package me.retrodaredevil.notificationfilter

import com.google.gson.*
import me.retrodaredevil.notificationfilter.data.*

private fun exportField(type: DataType, extra: DataExtra?, value: Any): JsonElement {
    when(type){
        DataType.ENUM -> TODO("implement enum")
        DataType.LIST -> {
            val array = JsonArray()
            val list = value as List<*>
            val listExtra = extra as ListExtra
            for(element in list) {
                array.add(exportField(listExtra.type, listExtra.extra, element!!))
            }
            return array
        }
        DataType.NUMBER -> return JsonPrimitive(value as Number)
        DataType.BOOLEAN -> return JsonPrimitive(value as Boolean)
        DataType.STRING -> return JsonPrimitive(value as String)
        DataType.STRING_PROVIDER -> return JsonPrimitive(value as String)
        DataType.MATCHER -> return exportFromMatcherData(value as MatcherData)
    }
}

fun exportFromMatcherData(matcherData: MatcherData): JsonObject {
    val jsonObject = JsonObject()
    jsonObject.addProperty("matcherType", matcherData.matcherType.name)
    val fields = JsonObject()
    for(field in matcherData.matcherType.fields){
        fields.add(field.key, exportField(field.type, field.extra, matcherData[field.key] ?: error("key: ${field.key} not present")))
    }
    jsonObject.add("fields", fields)
    return jsonObject
}