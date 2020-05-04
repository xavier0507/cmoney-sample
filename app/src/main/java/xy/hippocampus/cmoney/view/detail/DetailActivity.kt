package xy.hippocampus.cmoney.view.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_detail.*
import xy.hippocampus.cmoney.R
import xy.hippocampus.cmoney.extension.slideInRight
import xy.hippocampus.cmoney.extension.slideOutRight
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.share.CacheManager
import xy.hippocampus.cmoney.view.base.BaseActivity
import xy.hippocampus.cmoney.view.detail.task.ImageDownloadTask

class DetailActivity : BaseActivity<DetailViewModel>() {

    override val layoutRes: Int by lazy { R.layout.activity_detail }
    override val viewModel: DetailViewModel by lazy { DetailViewModel() }

    private val cacheManager: CacheManager by lazy { CacheManager.sharedInstance() }

    private val imageDownloadTask: ImageDownloadTask by lazy { ImageDownloadTask(this, image_photo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        imageDownloadTask.cancel(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        slideOutRight()
    }

    override fun subscribeObservers() {
        super.subscribeObservers()
        subscribeToClickBackButton()
    }

    private fun initLayout() {
        setSupportActionBar(toolbar)

        with(getDetail()) {
            text_id.text = getString(R.string.text_detail_content_id, getIdString())
            text_title.text = getString(R.string.text_detail_content_title, title)

            val bitmap = cacheManager.getBitmapFromMemoryCache(url)
            if (bitmap != null) {
                image_photo.setImageBitmap(bitmap)
            } else {
                imageDownloadTask.execute(url)
            }
        }

        toolbar.setNavigationOnClickListener { viewModel.clickBackButton() }
    }

    private fun getDetail() = intent.getParcelableExtra(EXTRA_DETAIL) ?: PhotoData.defaultInstance

    companion object {

        private const val EXTRA_DETAIL = "EXTRA_DETAIL"

        fun launch(activity: Activity, photoData: PhotoData) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_DETAIL, photoData)
            }
            with(activity) {
                startActivity(intent)
                slideInRight()
            }
        }
    }

    /***** Subscribe methods implementation *****/
    private fun subscribeToClickBackButton() {
        viewModel.clickBackButtonEvent.observe(this) {
            onBackPressed()
        }
    }
}