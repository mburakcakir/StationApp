package com.mburakcakir.stationapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mburakcakir.stationapp.R
import com.mburakcakir.stationapp.databinding.FragmentLocationBinding

class LocationFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<LocationFragmentArgs>()
    lateinit var location: com.mburakcakir.stationapp.network.model.Location
    private lateinit var mMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(args.location.latitude, args.location.longitude)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

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
        location = args.location
        Toast.makeText(requireContext(), location.toString(), Toast.LENGTH_SHORT).show()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val sydney = LatLng(location.latitude, location.longitude)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Location")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}