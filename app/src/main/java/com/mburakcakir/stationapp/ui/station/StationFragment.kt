package com.mburakcakir.stationapp.ui.station

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mburakcakir.stationapp.databinding.FragmentStationBinding
import com.mburakcakir.stationapp.network.model.Bus
import com.mburakcakir.stationapp.util.navigate
import com.mburakcakir.stationapp.util.sortBusList
import com.mburakcakir.stationapp.util.toast

class StationFragment : Fragment() {
    private var _binding: FragmentStationBinding? = null
    private val binding get() = _binding!!

    private val stationAdapter = StationAdapter()

    private val stationViewModel: StationViewModel by lazy {
        ViewModelProvider(this).get(StationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun init() {
        binding.rvStationList.adapter = stationAdapter
        stationViewModel.stationInfo.observe(viewLifecycleOwner) { responseStation ->
            binding.station = responseStation.stations[0]
            val sortedList = (responseStation.stations[0].buses as MutableList<Bus>).sortBusList()
//            val sortedList = (responseStation.stations[0].buses as MutableList<Bus>).sortList(responseStation.stations[0].buses[0].remainingTime)
            stationAdapter.submitList(sortedList)
        }

        stationAdapter.apply {
            setStationOnClickListener { bus ->
                this@StationFragment.navigate(
                    StationFragmentDirections.actionStationFragmentToLocationFragment(bus)
                )
            }
        }

        stationViewModel.result.observe(viewLifecycleOwner, {
            when {
                !it.success.isNullOrEmpty() -> it.success
                !it.loading.isNullOrEmpty() -> it.loading
                else -> it.error
            }?.let { message ->
                requireContext() toast message
            }
        })
    }

}