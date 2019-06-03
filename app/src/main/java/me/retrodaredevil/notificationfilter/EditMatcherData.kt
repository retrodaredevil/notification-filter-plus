package me.retrodaredevil.notificationfilter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import me.retrodaredevil.notificationfilter.data.DataField
import me.retrodaredevil.notificationfilter.data.MatcherData

class EditMatcherData : AppCompatActivity() {
    companion object {
        const val JSON_DATA = "EXTRA_JSON_DATA"
        private val GSON = GsonBuilder().create()
    }
    private lateinit var matcherData: MatcherData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_matcher_data)
        val intent = this.intent!!
        val jsonData = intent.getStringExtra(JSON_DATA) ?: throw NullPointerException("No JSON_DATA extra found in intent: $intent")
        matcherData = importFromJson(GSON.fromJson(jsonData, JsonObject::class.java))
        val matcherType = matcherData.matcherType
        for(field in matcherData.matcherType.fields){
            val displayName = matcherType.getFieldName(this, field)
            
        }
    }
}
