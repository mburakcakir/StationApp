package com.mburakcakir.stationapp.network.provider

import com.mburakcakir.stationapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BuildConfig.API_URL)
    .build()

object StationApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

