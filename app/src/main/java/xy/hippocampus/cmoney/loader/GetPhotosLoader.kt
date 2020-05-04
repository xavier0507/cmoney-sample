package xy.hippocampus.cmoney.loader

import android.content.Context
import xy.hippocampus.cmoney.api.JsonPlaceholderApi
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.parser.PhotoParser.toPhotoDataList

class GetPhotosLoader(
    context: Context
) : BaseAsyncTaskLoader<List<PhotoData>>(context) {

    private val albumId: Int = 1

    @Throws(Exception::class)
    override fun action(): String = JsonPlaceholderApi.getPhotos(context, albumId)

    @Throws(Exception::class)
    override fun parseData(jsonString: String): List<PhotoData> = toPhotoDataList(jsonString)
}