package com.gamechange.demo.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiManagers {

    private const val BASE_URL = "https://api.github.com/repos/firebase/firebase-ios-sdk/"

    val instance by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(HttpClient.instance)
            .build()
    }
}
