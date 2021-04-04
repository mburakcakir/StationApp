package com.mburakcakir.stationapp.ui.station

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mburakcakir.stationapp.data.repository.StationRepository
import com.mburakcakir.stationapp.data.repository.StationRepositoryImpl
import com.mburakcakir.stationapp.network.response.ResponseStation
import com.mburakcakir.stationapp.util.Result
import com.mburakcakir.stationapp.util.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class StationViewModel(application: Application) : AndroidViewModel(application) {

    private val stationRepository: StationRepository

    private val _result = MutableLiveData<Result>()
    val result: LiveData<Result> = _result

    private val _stationInfo = MutableLiveData<ResponseStation>()
    val stationInfo: LiveData<ResponseStation> = _stationInfo

    init {
        stationRepository = StationRepositoryImpl()
        getStationInfo()
    }

    fun getStationInfo() = viewModelScope.launch {
        stationRepository.getStationInfo()
            .onStart {
                _result.value = Result(loading = "İstasyon Bilgileri Yükleniyor")
            }
            .collect {
                when (it.status) {
                    Status.SUCCESS -> it.data?.let { responseStation ->
                        _stationInfo.value = responseStation
                        _result.value = Result(success = "İstasyon Bilgileri Yüklendi.")
                    }
                    Status.ERROR -> _result.value = Result(error = "Bir hata oluştu.")
                }
            }
    }
}