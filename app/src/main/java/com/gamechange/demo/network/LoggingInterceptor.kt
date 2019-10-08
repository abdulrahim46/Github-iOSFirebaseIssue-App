package com.gamechange.demo.network

import com.gamechange.demo.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {

    val instance by lazy {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}
