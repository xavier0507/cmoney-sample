package xy.hippocampus.cmoney.network

import java.io.Serializable

data class ApiResult<out T>(
    val data: T? = null,
    val exception: Exception? = null
) : Serializable