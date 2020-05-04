package xy.hippocampus.cmoney.view.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import xy.hippocampus.cmoney.extension.showCommonToast

abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        subscribeObservers()
    }

    @CallSuper
    protected open fun subscribeObservers() {
        subscribeToShowCommonToast()
    }

    /***** Subscribe methods implementation *****/
    private fun subscribeToShowCommonToast() {
        viewModel.showCommonToastEvent.observe(this) { event ->
            val message = event.peekContent()
            showCommonToast(message)
        }
    }
}