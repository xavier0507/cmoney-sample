package xy.hippocampus.cmoney.network

import android.content.Context
import android.net.Uri
import xy.hippocampus.cmoney.extension.setConfig
import xy.hippocampus.cmoney.util.Constants.API_HOST
import xy.hippocampus.cmoney.util.Constants.API_SCHEME
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object HttpUtil {

    const val REQUEST_METHOD_GET = "GET"

    @Throws(Exception::class)
    fun requestGet(context: Context, urlPath: String, params: Map<String, String>): String {
        val url = Uri.Builder().run {
            scheme(API_SCHEME)
            authority(API_HOST)
            appendPath(urlPath)
            params.forEach { (key, value) -> appendQueryParameter(key, value) }
            URL(toString())
        }
        return executeRequest(createGetConnection(context, url))
    }

    private fun createGetConnection(context: Context, url: URL): HttpsURLConnection {
        return (url.openConnection() as HttpsURLConnection).apply {
            setConfig(context)
            inputStream
        }
    }

    private fun executeRequest(connection: HttpURLConnection): String {
        return when (connection.responseCode) {
            HTTP_OK -> {
                try {
                    val stringBuilder = StringBuilder()
                    BufferedReader(InputStreamReader(connection.inputStream))
                        .apply {
                            forEachLine { stringBuilder.append(it) }
                            close()
                        }
                    stringBuilder.toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                    ""
                } finally {
                    connection.disconnect()
                }
            }
            else -> ""
        }
    }
}