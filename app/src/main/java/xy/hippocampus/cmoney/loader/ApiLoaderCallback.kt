package xy.hippocampus.cmoney.loader

import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import xy.hippocampus.cmoney.extension.showToast
import xy.hippocampus.cmoney.network.ApiResult
import xy.hippocampus.cmoney.loader.ApiLoaderCallback as ApiLoaderCallback1

abstract class ApiLoaderCallback<T> : LoaderManager.LoaderCallbacks<ApiResult<T>> {

    private var loaderManager: LoaderManager? = null
    private val loadId by lazy { generateLoaderId() }

    override fun onLoadFinished(loader: Loader<ApiResult<T>>, result: ApiResult<T>) {

        if (loaderManager != null) loaderManager?.destroyLoader(loadId)

        onPreLoadFinished()
        result.data?.let { onLoadedSuccess(it) }
        result.exception?.let { loader.context.showToast("Load api failed!") }
        onPostLoadFinished()
    }

    override fun onLoaderReset(loader: Loader<ApiResult<T>>) {
        this.loaderManager = null
    }

    protected open fun onPreLoadFinished() {
        //Default function is to do nothing
    }

    protected open fun onPostLoadFinished() {
        //Default function is to do nothing
    }

    abstract fun onLoadedSuccess(data: T)

    fun setLoadManager(loaderManager: LoaderManager) {
        this.loaderManager = loaderManager
    }

    private fun generateLoaderId() = (Math.random() * 20000).toInt() + 10000

    companion object {

        fun startApiLoader(loaderManager: LoaderManager, callback: ApiLoaderCallback1<*>) {
            callback.setLoadManager(loaderManager)
            loaderManager.restartLoader(callback.loadId, null, callback)
        }
    }
}