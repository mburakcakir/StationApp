package com.mburakcakir.stationapp.network.provider

import com.mburakcakir.stationapp.network.response.ResponseStation
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("stations")
    suspend fun getStationInfo(): Response<ResponseStation>
}