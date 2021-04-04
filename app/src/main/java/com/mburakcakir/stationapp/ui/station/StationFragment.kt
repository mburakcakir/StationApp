package com.mburakcakir.stationapp.ui.station

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mburakcakir.stationapp.R
import com.mburakcakir.stationapp.databinding.FragmentStationBinding
import com.mburakcakir.stationapp.network.model.Bus
import com.mburakcakir.stationapp.util.*

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
                !it.success.isNullOrEmpty() -> {
                    binding.state = StationFragmentViewState(Status.SUCCESS)
                    requireContext() toast it.success
                }
                !it.loading.isNullOrEmpty() -> {
                    binding.state = StationFragmentViewState(Status.LOADING)
                }
                else -> {
                    val internetConnection =
                        NetworkController(requireContext()).isInternetAvailable()
                    binding.message =
                        if (!internetConnection) getString(R.string.error_internet_connection)
                        else it.error

                    binding.state = StationFragmentViewState(Status.ERROR)

                }
            }

        })
    }
}