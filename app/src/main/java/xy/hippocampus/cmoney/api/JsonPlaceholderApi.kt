package xy.hippocampus.cmoney.api

import android.content.Context
import xy.hippocampus.cmoney.network.HttpUtil

object JsonPlaceholderApi {

    private const val GET_PHOTOS = "photos"

    private const val pageLimitation = 50

    fun getPhotos(context: Context, startIndex: Int): String =
        HttpUtil.requestGet(
            context, GET_PHOTOS,
            mapOf(
                "_start" to startIndex.toString(),
                "_limit" to pageLimitation.toString()
            )
        )
}