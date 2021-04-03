package com.mburakcakir.stationapp.data.repository

import com.mburakcakir.stationapp.network.provider.StationApi
import com.mburakcakir.stationapp.network.response.ResponseStation
import com.mburakcakir.stationapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StationRepositoryImpl : StationRepository {

    private val apiService = StationApi.retrofitService

    override suspend fun getStationInfo(): Flow<Resource<ResponseStation>> = flow {
        try {
            val response = apiService.getStationInfo()
            if (response.isSuccessful) {
                response.body().apply {
                    emit(Resource.Success(this))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}