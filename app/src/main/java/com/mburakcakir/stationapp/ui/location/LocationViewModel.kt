package com.mburakcakir.stationapp.ui.location

import android.app.Application
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mburakcakir.stationapp.network.model.Bus
import java.util.*

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private var geocoder: Geocoder = Geocoder(application, Locale.getDefault())
    lateinit var markerOnClick: () -> Unit
    private fun getLocationAddressByLatLng(currentLocation: LatLng): String {

        val addresses = geocoder.getFromLocation(
            currentLocation.latitude,
            currentLocation.longitude,
            1
        )
        return addresses[0].getAddressLine(0)
    }

    fun setOnMarkerClickListener(markerOnClick: () -> Unit) {
        this.markerOnClick = markerOnClick
    }

    fun setGoogleMapCallback(bus: Bus, zoom: Float, duration: Int): OnMapReadyCallback {
        return OnMapReadyCallback { googleMap ->
            val location = bus.location
            val currentLocation = LatLng(location.latitude, location.longitude)

            googleMap.apply {

                addMarker(
                    MarkerOptions()
                        .position(currentLocation)
                        .title(bus.plate)
                        .snippet(getLocationAddressByLatLng(currentLocation))
                        .visible(true)

                ).showInfoWindow()

                setOnInfoWindowClickListener {
                    markerOnClick.invoke()
                }

                moveCamera(CameraUpdateFactory.newLatLng(currentLocation))

                animateCamera(
                    CameraUpdateFactory.newLatLngZoom(currentLocation, zoom),
                    duration,
                    null
                )
//                animateCamera(CameraUpdateFactory.newCameraPosition(setCameraPosition(currentLocation)))
            }
        }
    }

    //private fun setCameraPosition(currentLocation: LatLng): CameraPosition? {
//        return CameraPosition.builder()
//            .target(currentLocation)
//            .zoom(18f)
//            .tilt(0f)
//            .bearing(0f)
//            .build()
//    }
}