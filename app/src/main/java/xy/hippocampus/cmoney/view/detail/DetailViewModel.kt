package xy.hippocampus.cmoney.view.detail

import androidx.lifecycle.MutableLiveData
import xy.hippocampus.cmoney.share.Event
import xy.hippocampus.cmoney.view.base.BaseViewModel

class DetailViewModel : BaseViewModel() {

    private val _clickBackButtonEvent by lazy { MutableLiveData<Event<Unit>>() }
    val clickBackButtonEvent by lazy { _clickBackButtonEvent }

    fun clickBackButton() {
        _clickBackButtonEvent.value = Event(Unit)
    }
}