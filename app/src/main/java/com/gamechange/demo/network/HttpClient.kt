package com.gamechange.demo.network

import okhttp3.OkHttpClient

object HttpClient {
    val instance: OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            addInterceptor(LoggingInterceptor.instance)
        }.build()
    }
}
