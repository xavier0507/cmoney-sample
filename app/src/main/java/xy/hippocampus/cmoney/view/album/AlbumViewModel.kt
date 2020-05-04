package xy.hippocampus.cmoney.view.album

import android.content.Context
import androidx.lifecycle.MutableLiveData
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.share.Event
import xy.hippocampus.cmoney.view.album.task.GetPhotosTask
import xy.hippocampus.cmoney.view.base.BaseViewModel

class AlbumViewModel : BaseViewModel() {

    private val _viewReadyEvent by lazy { MutableLiveData<Event<List<PhotoData>>>() }
    val viewReadyEvent by lazy { _viewReadyEvent }

    private val _cancelAllImageDownloadTaskEvent by lazy { MutableLiveData<Event<Unit>>() }
    val cancelAllImageDownloadTaskEvent by lazy { _cancelAllImageDownloadTaskEvent }

    private val _clickBackButtonEvent by lazy { MutableLiveData<Event<Unit>>() }
    val clickBackButtonEvent by lazy { _clickBackButtonEvent }

    private val _clickThumbnailButtonEvent by lazy { MutableLiveData<Event<PhotoData>>() }
    val clickThumbnailButtonEvent by lazy { _clickThumbnailButtonEvent }

    fun viewReady(context: Context, startIndex: Int = 0) {
        GetPhotosTask(context, _viewReadyEvent).execute(startIndex)
    }

    fun onDestroy() {
        _cancelAllImageDownloadTaskEvent.value = Event(Unit)
    }

    fun clickBackButton() {
        _clickBackButtonEvent.value = Event(Unit)
    }

    fun clickThumbnailButton(photoData: PhotoData) {
        _clickThumbnailButtonEvent.value = Event(photoData)
    }
}