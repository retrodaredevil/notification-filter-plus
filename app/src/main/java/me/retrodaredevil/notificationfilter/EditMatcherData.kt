package me.retrodaredevil.notificationfilter

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.EditTextPreference
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import me.retrodaredevil.notificationfilter.data.*

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
        val jsonData = intent.getStringExtra(JSON_DATA) ?: error("No JSON_DATA extra found in intent: $intent")
        println("jsonData: $jsonData")
        matcherData = importFromJson(GSON.fromJson(jsonData, JsonObject::class.java))

        val linearLayout = findViewById<LinearLayout>(R.id.fields_linear_layout)

        val matcherType = matcherData.matcherType
        for(field in matcherData.matcherType.fields){
            val displayName = matcherType.getFieldName(this, field)
            val row = LinearLayout(this)
            row.orientation = LinearLayout.HORIZONTAL
            linearLayout.addView(row)

            val textView = TextView(this)
            textView.text = displayName
            row.addView(textView)
            when(field.type){
                DataType.MATCHER -> row.addView(Button(this).apply { text = "edit data" })  // TODO hard coded string value
                DataType.STRING_PROVIDER -> row.addView(Spinner(this).apply {
                    val list = PROVIDER_TYPE_MAP.values.map { it.getDisplayName(this@EditMatcherData) }
                    adapter = ArrayAdapter<String>(this@EditMatcherData, android.R.layout.simple_spinner_dropdown_item, list)
                })
                DataType.BOOLEAN -> row.addView(CheckBox(this))
                DataType.STRING -> row.addView(EditText(this))
                DataType.NUMBER -> row.addView(EditText(this).apply {
                    val numberExtra = field.extra as NumberExtra
                    var type = EditorInfo.TYPE_CLASS_NUMBER
                    if(!numberExtra.isInteger){
                        type = type or EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
                    }
                    if(!numberExtra.isUnsigned){
                        type = type or EditorInfo.TYPE_NUMBER_FLAG_SIGNED
                    }
                    inputType = type
                })
                DataType.LIST -> row.addView(Button(this).apply { text = "edit data" })
                DataType.ENUM -> TODO("implement enum")
            }
        }
    }
    fun onDone(view: View){
        val intent = Intent()
        intent.putExtra(JSON_DATA, GSON.toJson(exportFromMatcherData(matcherData)))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
    fun onSaveAs(view: View){
        TODO("implement saving")
    }
}
