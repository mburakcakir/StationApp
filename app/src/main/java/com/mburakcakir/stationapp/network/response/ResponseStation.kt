package com.mburakcakir.stationapp.network.response

import com.mburakcakir.stationapp.network.model.Station

data class ResponseStation(
    val stations: List<Station>
)