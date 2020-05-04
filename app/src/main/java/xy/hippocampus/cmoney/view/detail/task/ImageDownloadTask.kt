package xy.hippocampus.cmoney.view.detail.task

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.widget.ImageView
import xy.hippocampus.cmoney.share.CacheManager
import xy.hippocampus.cmoney.util.BitmapUtil.downloadBitmap
import java.io.IOException

class ImageDownloadTask(
    private val context: Context,
    private val imageView: ImageView,
    private val onPostExecuteListener: (ImageDownloadTask) -> Unit = {}
) : AsyncTask<String, Void, Bitmap>() {

    private val cacheManager: CacheManager by lazy { CacheManager.sharedInstance() }

    private val width = 120
    private val height = 120

    override fun doInBackground(vararg params: String): Bitmap? {
        val imageUrl = params[0]
        var bitmap: Bitmap? = null

        try {
            bitmap = downloadBitmap(context, imageUrl, width, height)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (bitmap != null) cacheManager.addBitmapToMemoryCache(imageUrl, bitmap)

        return bitmap;
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        bitmap?.let { imageView.setImageBitmap(it) }
        onPostExecuteListener(this)
    }
}