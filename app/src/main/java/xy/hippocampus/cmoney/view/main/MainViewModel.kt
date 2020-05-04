package xy.hippocampus.cmoney.view.main

import androidx.lifecycle.MutableLiveData
import xy.hippocampus.cmoney.share.Event
import xy.hippocampus.cmoney.view.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    private val _launchGalleryPageEvent by lazy { MutableLiveData<Event<Unit>>() }
    val launchGalleryPageEvent by lazy { _launchGalleryPageEvent }

    fun launchGalleryPage() {
        _launchGalleryPageEvent.value = Event(Unit)
    }
}