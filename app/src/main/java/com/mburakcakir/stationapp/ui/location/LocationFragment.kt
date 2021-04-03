package com.mburakcakir.stationapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mburakcakir.stationapp.R
import com.mburakcakir.stationapp.databinding.FragmentLocationBinding

class LocationFragment : Fragment() {
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<LocationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(googleMapCallback)
    }

    private val googleMapCallback = OnMapReadyCallback { googleMap ->
        val location = args.bus.location
        val currentLocation = LatLng(location.latitude, location.longitude)
        googleMap.apply {
            addMarker(MarkerOptions().position(currentLocation).title("Marker in Sydney"))
            moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
        }
    }
}