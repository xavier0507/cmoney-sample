package xy.hippocampus.cmoney.view.album

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_album.*
import xy.hippocampus.cmoney.R
import xy.hippocampus.cmoney.extension.slideInRight
import xy.hippocampus.cmoney.extension.slideOutRight
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.view.base.BaseActivity
import xy.hippocampus.cmoney.view.detail.DetailActivity
import xy.hippocampus.cmoney.view.util.PaginationScrollListener.Companion.createPaginationScrollListener

class AlbumActivity : BaseActivity<AlbumViewModel>() {

    override val layoutRes: Int by lazy { R.layout.activity_album }
    override val viewModel: AlbumViewModel by lazy { AlbumViewModel() }

    private val albumAdapter: AlbumAdapter by lazy { AlbumAdapter(::clickThumbnailButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        viewModel.viewReady(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        slideOutRight()
    }

    override fun subscribeObservers() {
        super.subscribeObservers()
        subscribeToViewReady()
        subscribeToCancelAllImageDownloadTaskEvent()
        subscribeToClickBackButton()
        subscribeToClickThumbnailButton()
    }

    private fun initLayout() {
        setSupportActionBar(toolbar)

        with(recycler_view_thumbnail) {
            adapter = albumAdapter
            addOnScrollListener(
                createPaginationScrollListener(
                    layoutManager as LinearLayoutManager,
                    ::loadMorePhotoData
                )
            )
        }

        toolbar.setNavigationOnClickListener { viewModel.clickBackButton() }
    }

    private fun clickThumbnailButton(photoData: PhotoData) {
        viewModel.clickThumbnailButton(photoData)
    }

    private fun loadMorePhotoData(startIndex: Int) = viewModel.viewReady(this, startIndex)

    companion object {

        fun launch(activity: Activity) {
            val intent = Intent(activity, AlbumActivity::class.java)
            with(activity) {
                startActivity(intent)
                slideInRight()
            }
        }
    }

    /***** Subscribe methods implementation *****/
    private fun subscribeToViewReady() {
        viewModel.viewReadyEvent.observe(this) { event ->
            val photoDataList = event.peekContent()
            albumAdapter.updateData(photoDataList)
        }
    }

    private fun subscribeToCancelAllImageDownloadTaskEvent() {
        viewModel.cancelAllImageDownloadTaskEvent.observe(this) {
            albumAdapter.cancelAllTasks()
        }
    }

    private fun subscribeToClickBackButton() {
        viewModel.clickBackButtonEvent.observe(this) {
            onBackPressed()
        }
    }

    private fun subscribeToClickThumbnailButton() {
        viewModel.clickThumbnailButtonEvent.observe(this) { event ->
            val detailViewInfo = event.peekContent()
            DetailActivity.launch(this, detailViewInfo)
        }
    }
}