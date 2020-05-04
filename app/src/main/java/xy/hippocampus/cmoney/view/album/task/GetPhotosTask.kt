package xy.hippocampus.cmoney.view.album.task

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import xy.hippocampus.cmoney.api.JsonPlaceholderApi.getPhotos
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.parser.PhotoParser.toPhotoDataList
import xy.hippocampus.cmoney.share.Event

class GetPhotosTask(
    private val context: Context,
    private val _viewReadyEvent: MutableLiveData<Event<List<PhotoData>>>
) : AsyncTask<Int, Void, List<PhotoData>>() {

    override fun doInBackground(vararg params: Int?): List<PhotoData> {
        return toPhotoDataList(getPhotos(context, params[0] ?: 0))
    }

    override fun onPostExecute(result: List<PhotoData>) {
        _viewReadyEvent.value = Event(result)
    }
}