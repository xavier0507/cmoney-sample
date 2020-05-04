package xy.hippocampus.cmoney.network

import java.io.Serializable

class ApiResult<T> : Serializable {
    private val serialVersionUID = 1L

    var data: T? = null
    var exception: Exception = Exception("No data")
}