package xy.hippocampus.cmoney.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xy.hippocampus.cmoney.share.Event

abstract class BaseViewModel : ViewModel() {

    private val _showCommonToastEvent by lazy { MutableLiveData<Event<String>>() }
    val showCommonToastEvent: LiveData<Event<String>> by lazy { _showCommonToastEvent }

    fun showCommonToastEvent(message: String) {
        _showCommonToastEvent.postValue(Event(message))
    }
}