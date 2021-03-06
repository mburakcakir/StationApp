package com.mburakcakir.stationapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.SupportMapFragment
import com.mburakcakir.stationapp.R
import com.mburakcakir.stationapp.databinding.FragmentLocationBinding
import com.mburakcakir.stationapp.util.navigate


class LocationFragment : Fragment() {
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<LocationFragmentArgs>()

    private val locationViewModel by lazy {
        ViewModelProvider(this).get(LocationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val bus = args.bus
        val location = bus.location

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync(
            locationViewModel.setGoogleMapCallback(
                bus = bus,
                zoom = 18f,
                duration = 100
            )
        )

        locationViewModel.setOnMarkerClickListener {
            this.navigate(LocationFragmentDirections.actionLocationFragmentToMapDialog(location))
        }
    }

}
