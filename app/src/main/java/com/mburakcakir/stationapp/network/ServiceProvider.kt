package com.mburakcakir.stationapp.network

import com.mburakcakir.stationapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .build()

object StationApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

