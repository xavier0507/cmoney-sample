package xy.hippocampus.cmoney.view.album

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_thumbnail.view.*
import xy.hippocampus.cmoney.R
import xy.hippocampus.cmoney.model.PhotoData

class AlbumAdapter(
    private val downloadImageListener: (ImageView, PhotoData) -> Unit,
    private val onClickThumbnailButtonListener: (PhotoData) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var dataList: List<PhotoData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataList[position], downloadImageListener, onClickThumbnailButtonListener)

    fun updateData(dataList: List<PhotoData>) {
        this.dataList = this.dataList.plus(dataList)
        notifyDataSetChanged()
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_thumbnail, parent, false)
    ) {

        fun bind(
            item: PhotoData,
            downloadImageListener: (ImageView, PhotoData) -> Unit,
            onClickThumbnailButtonListener: (PhotoData) -> Unit
        ) {
            with(itemView) {
                text_id.text = item.id.toString()
                text_title.text = item.title
                downloadImageListener(image_thumbnail, item)
                setOnClickListener { onClickThumbnailButtonListener(item) }
            }
        }
    }
}