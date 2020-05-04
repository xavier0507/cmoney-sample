package xy.hippocampus.cmoney.loader

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import xy.hippocampus.cmoney.network.ApiResult

abstract class BaseAsyncTaskLoader<T>(
    context: Context
) : AsyncTaskLoader<ApiResult<T>>(context) {

    override fun onStartLoading() {
        forceLoad()
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun loadInBackground(): ApiResult<T> {
        var result = ApiResult<T>()

        result = try {
            val response = action()
            result.copy(data = parseData(response))
        } catch (e: Exception) {
            result.copy(exception = e)
        }

        return result
    }

    @Throws(Exception::class)
    protected abstract fun action(): String

    @Throws(Exception::class)
    protected abstract fun parseData(jsonString: String): T
}