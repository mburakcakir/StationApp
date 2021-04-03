package com.mburakcakir.stationapp.ui.station

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mburakcakir.stationapp.databinding.FragmentStationBinding

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
            binding.station = responseStation.stations[0].stationName
            stationAdapter.submitList(responseStation.stations[0].buses)
        }

        stationAdapter.apply {
            setStationOnClickListener { location ->
//                this@StationFragment.navigate(StationFragmentDirections.actionStationFragmentToLocationFragment(location))
                findNavController().navigate(
                    StationFragmentDirections.actionStationFragmentToLocationFragment(
                        location
                    )
                )
//                this@StationFragment.navigate(StationFragmentDirections.actionStationFragmentToMapsFragment())
            }
        }
    }

}