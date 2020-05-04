package xy.hippocampus.cmoney.share

import android.graphics.Bitmap
import android.util.LruCache

class CacheManager {

    private val memoryCache: LruCache<String, Bitmap> by lazy { createMemoryCache() }
    private val maxMemory = Runtime.getRuntime().maxMemory().toInt()
    private val cacheSize = maxMemory / 8

    fun getBitmapFromMemoryCache(key: String): Bitmap? = memoryCache.get(key)

    fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {
        if (getBitmapFromMemoryCache(key) == null) memoryCache.put(key, bitmap)
    }

    private fun createMemoryCache(): LruCache<String, Bitmap> =
        object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return bitmap.byteCount / 1024
            }
        }

    companion object {
        private val manager by lazy { CacheManager() }

        fun sharedInstance(): CacheManager = manager
    }
}