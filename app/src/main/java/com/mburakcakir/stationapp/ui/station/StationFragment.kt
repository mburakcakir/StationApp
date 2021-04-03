package com.mburakcakir.stationapp.ui.station

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mburakcakir.stationapp.databinding.FragmentHomeBinding

class StationFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val stationAdapter = StationAdapter()

    private val stationViewModel: StationViewModel by lazy {
        ViewModelProvider(this).get(StationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
            Log.v("buses", responseStation.stations[0].buses.toString())
            stationAdapter.submitList(responseStation.stations[0].buses)
        }
    }

}