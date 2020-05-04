package xy.hippocampus.cmoney.view.album

import android.widget.ImageView
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.share.CacheManager
import xy.hippocampus.cmoney.view.detail.task.ImageDownloadTask

class AlbumHelper {

    private var taskList: ArrayList<ImageDownloadTask> = arrayListOf()
    private val cacheManager: CacheManager by lazy { CacheManager.sharedInstance() }

    // TODO Should use a placeholder replace to white background and run a progress bar
    fun downloadImage(imageView: ImageView, item: PhotoData) {
        val thumbnailUrl = item.thumbnailUrl
        val bitmap = cacheManager.getBitmapFromMemoryCache(thumbnailUrl)

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
        } else {
            val imageDownloadTask = ImageDownloadTask(
                imageView.context, imageView, ::removeTask
            )
                .apply { execute(thumbnailUrl) }
            taskList.add(imageDownloadTask)
        }
    }

    fun cancelAllTasks() {
        if (taskList.isNotEmpty()) taskList.forEach { it.cancel(true) }
    }

    private fun removeTask(task: ImageDownloadTask) {
        taskList.remove(task)
    }
}