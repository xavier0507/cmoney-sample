package xy.hippocampus.cmoney.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import xy.hippocampus.cmoney.extension.setConfig
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object BitmapUtil {

    @Throws(IOException::class)
    fun downloadBitmap(context: Context, imageUrl: String, width: Int, height: Int): Bitmap? {
        val options = BitmapFactory.Options().apply {
            inSampleSize = calculateImageSampleSize(this, width, height)
            inJustDecodeBounds = false
        }
        return getInputStreamFromURL(context, imageUrl).use {
            BitmapFactory.decodeStream(it, null, options)
        }
    }

    @Throws(IOException::class)
    private fun getInputStreamFromURL(context: Context, imageUrl: String): InputStream? {
        return (URL(imageUrl).openConnection() as HttpsURLConnection).run {
            setConfig(context)
            inputStream
        }
    }

    private fun calculateImageSampleSize(
        options: BitmapFactory.Options, width: Int, height: Int
    ): Int {
        val rawH = options.outHeight
        val rawW = options.outWidth
        var imageSampleSize = 1

        if (rawH > height || rawW > width) {
            val halfH = rawH / 2
            val halfW = rawW / 2

            while (halfH / imageSampleSize > height && halfW / imageSampleSize > width) {
                imageSampleSize *= 2
            }
        }

        return imageSampleSize
    }
}