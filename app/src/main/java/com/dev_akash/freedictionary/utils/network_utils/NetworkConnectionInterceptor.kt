package com.dev_akash.freedictionary.utils.network_utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        return if (!isConnected) {
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(502)
                .message("Please check your internet connection")
                .body("{Network Not Connected}".toResponseBody(null)).build()
        } else {
            try {
                val builder = request.newBuilder()
                chain.proceed(builder.build())
            } catch (exception: Exception) {
                Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(502)
                    .message(exception.message ?: "Unknown Error")
                    .body("{${exception}}".toResponseBody(null)).build()
            }
        }
    }

    private val isConnected: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

}