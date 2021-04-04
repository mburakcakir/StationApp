package com.mburakcakir.stationapp.ui.station

import android.view.View
import com.mburakcakir.stationapp.util.Status

class StationFragmentViewState(private val status: Status) {
    fun getProgressBarVisibility() = if (status == Status.LOADING) View.VISIBLE else View.GONE
    fun getRecyclerViewVisibility() = if (status == Status.SUCCESS) View.VISIBLE else View.GONE
    fun getErrorMessageVisibility() = if (status == Status.ERROR) View.VISIBLE else View.GONE
}