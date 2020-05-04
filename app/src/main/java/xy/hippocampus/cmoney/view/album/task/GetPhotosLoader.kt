package xy.hippocampus.cmoney.view.album.task

import android.content.Context
import xy.hippocampus.cmoney.api.JsonPlaceholderApi
import xy.hippocampus.cmoney.loader.BaseAsyncTaskLoader
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.parser.PhotoParser.toPhotoDataList

class GetPhotosLoader(
    context: Context,
    private val startIndex: Int
) : BaseAsyncTaskLoader<List<PhotoData>>(context) {

    @Throws(Exception::class)
    override fun action(): String {
        return JsonPlaceholderApi.getPhotos(context, startIndex)
    }

    @Throws(Exception::class)
    override fun parseData(jsonString: String): List<PhotoData> {
        return toPhotoDataList(jsonString)
    }
}