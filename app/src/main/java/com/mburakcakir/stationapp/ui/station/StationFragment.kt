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

        checkInternetConnection()

        observeStation()

        setAdapter()

        observeResult()

        infoOnClick()
    }

    private fun observeResult() {
        stationViewModel.result.observe(viewLifecycleOwner) { result ->
            when {
                !result.success.isNullOrEmpty() -> {
                    binding.state = StationFragmentViewState(Status.SUCCESS)
                    requireContext() toast result.success
                }
                !result.loading.isNullOrEmpty() -> {
                    binding.state = StationFragmentViewState(Status.LOADING)
                }
                else -> {
                    binding.state = StationFragmentViewState(Status.ERROR)
                    result.error?.let { error ->
                        requireContext() toast error
                    }

                }

            }
        }
    }

    private fun setAdapter() {
        binding.rvStationList.adapter = stationAdapter

        stationAdapter.apply {
            setStationOnClickListener { bus ->
                this@StationFragment.navigate(
                    StationFragmentDirections.actionStationFragmentToLocationFragment(bus)
                )
            }
        }
    }

    private fun observeStation() {
        stationViewModel.stationInfo.observe(viewLifecycleOwner) { responseStation ->
            binding.station = responseStation.stations[0]
            val sortedList = (responseStation.stations[0].buses as MutableList<Bus>).sortBusList()
            stationAdapter.submitList(sortedList)
        }
    }

    private fun checkStationDataAndSetState() {
        if (stationViewModel.stationInfo.value == null)
            stationViewModel.getStationInfo()
        else {
            binding.state = StationFragmentViewState(Status.SUCCESS)
        }
    }

    private fun checkInternetConnection() {
        val networkController = NetworkController(requireContext()).apply {
            startNetworkCallback()
        }

        networkController.isNetworkConnected.observe(viewLifecycleOwner) { internetConnection ->
            if (internetConnection)
                checkStationDataAndSetState()
            else
                binding.state = StationFragmentViewState(Status.ERROR)
        }
    }

    private fun infoOnClick() {
        binding.includeHeader.imgInfo.setOnClickListener {
            requireContext().showDialogInfo(getString(R.string.dialog_info))
        }
    }

}