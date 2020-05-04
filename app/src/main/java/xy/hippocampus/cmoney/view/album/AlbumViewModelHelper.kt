package xy.hippocampus.cmoney.view.album

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.loader.content.Loader
import xy.hippocampus.cmoney.loader.ApiLoaderCallback
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.network.ApiResult
import xy.hippocampus.cmoney.share.Event
import xy.hippocampus.cmoney.view.album.task.GetPhotosLoader

class AlbumViewModelHelper(
    private val _viewReadyEvent: MutableLiveData<Event<List<PhotoData>>>
) {

    fun createGetPhotosCallback(
        context: Context, startIndex: Int
    ): ApiLoaderCallback<List<PhotoData>> {
        return object : ApiLoaderCallback<List<PhotoData>>() {

            override fun onCreateLoader(
                id: Int, args: Bundle?
            ): Loader<ApiResult<List<PhotoData>>> {
                return GetPhotosLoader(context, startIndex)
            }

            override fun onLoadedSuccess(data: List<PhotoData>) {
                _viewReadyEvent.value = Event(data)
            }
        }
    }
}