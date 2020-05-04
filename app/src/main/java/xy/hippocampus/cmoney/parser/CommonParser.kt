package xy.hippocampus.cmoney.parser

import org.json.JSONArray
import org.json.JSONObject

object CommonParser {

    fun getInt(json: JSONObject, key: String?): Int =
        json.takeIf { it.has(key) && !json.isNull(key) }
            ?.let { json.optInt(key, -1) }
            ?: -1

    fun getString(json: JSONObject, key: String?): String =
        json.takeIf { it.has(key) && !json.isNull(key) }
            ?.let { json.optString(key, "") }
            ?: ""

    fun getJSONArrayWithoutKey(jsonArray: JSONArray): List<JSONObject> =
        (0 until jsonArray.length()).map { index -> jsonArray.getJSONObject(index) }
}