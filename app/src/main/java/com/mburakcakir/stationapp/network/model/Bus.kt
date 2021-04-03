package com.mburakcakir.stationapp.network.model

data class Bus(
    val icon: String,
    val location: Location,
    val plate: String,
    val remainingTime: Int,
    val routeCode: String
)