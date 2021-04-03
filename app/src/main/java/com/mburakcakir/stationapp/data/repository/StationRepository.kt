package com.mburakcakir.stationapp.data.repository

import com.mburakcakir.stationapp.network.response.ResponseStation
import com.mburakcakir.stationapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StationRepository {
    suspend fun getStationInfo(): Flow<Resource<ResponseStation>>
}