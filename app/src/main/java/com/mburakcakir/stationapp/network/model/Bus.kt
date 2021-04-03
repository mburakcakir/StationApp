package com.mburakcakir.stationapp.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bus(
    val icon: String,
    val location: Location,
    val plate: String,
    val remainingTime: Int,
    val routeCode: String
) : Parcelable