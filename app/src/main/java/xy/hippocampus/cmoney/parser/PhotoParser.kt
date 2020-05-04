package xy.hippocampus.cmoney.parser

import org.json.JSONArray
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.parser.CommonParser.getInt
import xy.hippocampus.cmoney.parser.CommonParser.getJSONArrayWithoutKey
import xy.hippocampus.cmoney.parser.CommonParser.getString

object PhotoParser {

    @Throws(Exception::class)
    fun toPhotoDataList(jsonString: String): List<PhotoData> =
        jsonString.takeIf(String::isNotEmpty)
            ?.run {
                getJSONArrayWithoutKey(JSONArray(jsonString)).map { jsonObject ->
                    PhotoData(
                        getInt(jsonObject, "albumId"),
                        getInt(jsonObject, "id"),
                        getString(jsonObject, "title"),
                        getString(jsonObject, "url"),
                        getString(jsonObject, "thumbnailUrl")
                    )
                }
            } ?: listOf()
}