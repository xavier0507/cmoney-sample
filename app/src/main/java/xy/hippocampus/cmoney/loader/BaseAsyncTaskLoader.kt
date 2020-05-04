package xy.hippocampus.cmoney.loader

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import xy.hippocampus.cmoney.network.ApiResult

abstract class BaseAsyncTaskLoader<T>(
    context: Context
) : AsyncTaskLoader<ApiResult<T>>(context) {

    override fun loadInBackground(): ApiResult<T> {
        val result = ApiResult<T>()

        try {
            val response = action()
            result.data = parseData(response)
        } catch (e: Exception) {
            result.exception = e
        }

        return result
    }

    @Throws(Exception::class)
    protected abstract fun action(): String

    @Throws(Exception::class)
    protected abstract fun parseData(jsonString: String): T
}