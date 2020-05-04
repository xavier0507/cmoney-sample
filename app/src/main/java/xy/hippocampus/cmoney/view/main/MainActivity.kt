package xy.hippocampus.cmoney.view.main

import android.os.Bundle
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.*
import xy.hippocampus.cmoney.R
import xy.hippocampus.cmoney.view.album.AlbumActivity
import xy.hippocampus.cmoney.view.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override val layoutRes: Int by lazy { R.layout.activity_main }
    override val viewModel: MainViewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    override fun subscribeObservers() {
        super.subscribeObservers()
        subscribeToLaunchGalleryPage()
    }

    private fun initLayout() {
        button_send.setOnClickListener { viewModel.launchGalleryPage() }
    }

    /***** Subscribe methods implementation *****/
    private fun subscribeToLaunchGalleryPage() {
        viewModel.launchGalleryPageEvent.observe(this) {
            AlbumActivity.launch(this)
        }
    }
}