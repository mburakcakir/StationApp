package com.mburakcakir.stationapp.network.model

data class Station(
    val buses: List<Bus>,
    val location: Location,
    val stationCode: String,
    val stationName: String
)