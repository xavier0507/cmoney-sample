package xy.hippocampus.cmoney.view.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_thumbnail.view.*
import xy.hippocampus.cmoney.R
import xy.hippocampus.cmoney.model.PhotoData
import xy.hippocampus.cmoney.share.CacheManager
import xy.hippocampus.cmoney.view.detail.task.ImageDownloadTask

class AlbumAdapter(
    private val onClickThumbnailButtonListener: (PhotoData) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var dataList: List<PhotoData> = listOf()
    private var taskList: ArrayList<ImageDownloadTask> = arrayListOf()
    private val cacheManager: CacheManager by lazy { CacheManager.sharedInstance() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent)

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataList[position], onClickThumbnailButtonListener)

    fun updateData(dataList: List<PhotoData>) {
        this.dataList = this.dataList.plus(dataList)
        notifyDataSetChanged()
    }

    fun cancelAllTasks() {
        if (taskList.isNotEmpty()) taskList.forEach { it.cancel(true) }
    }

    fun removeTask(task: ImageDownloadTask) {
        taskList.remove(task)
    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_thumbnail, parent, false)
    ) {

        fun bind(
            item: PhotoData,
            onClickThumbnailButtonListener: (PhotoData) -> Unit
        ) {
            with(itemView) {
                text_id.text = item.id.toString()
                text_title.text = item.title
                downloadImage(item)
                setOnClickListener { onClickThumbnailButtonListener(item) }
            }
        }

        private fun downloadImage(item: PhotoData) {
            with(itemView) {
                val thumbnailUrl = item.thumbnailUrl
                val bitmap = cacheManager.getBitmapFromMemoryCache(thumbnailUrl)

                if (bitmap != null) {
                    image_thumbnail.setImageBitmap(bitmap)
                } else {
                    val imageDownloadTask = ImageDownloadTask(
                        context, image_thumbnail, ::removeTask
                    ).apply { execute(thumbnailUrl) }
                    taskList.add(imageDownloadTask)
                }
            }
        }
    }
}