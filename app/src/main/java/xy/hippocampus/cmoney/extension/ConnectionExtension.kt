package xy.hippocampus.cmoney.extension

import android.content.Context
import android.webkit.WebSettings
import org.apache.http.protocol.HTTP.USER_AGENT
import xy.hippocampus.cmoney.network.HttpUtil
import javax.net.ssl.HttpsURLConnection

fun HttpsURLConnection.setConfig(context: Context): HttpsURLConnection {
    setRequestProperty(USER_AGENT, WebSettings.getDefaultUserAgent(context))
    requestMethod = HttpUtil.REQUEST_METHOD_GET
    connectTimeout = 20 * 1000
    readTimeout = 20 * 1000
    doInput = true
    return this
}