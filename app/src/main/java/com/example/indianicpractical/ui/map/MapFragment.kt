package com.example.indianicpractical.ui.map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.indianicpractical.R
import com.example.indianicpractical.databinding.FragmentMapBinding
import com.example.indianicpractical.utils.AppConstant
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {
    var map: GoogleMap? = null
    lateinit var binding: FragmentMapBinding
    var mFusedLocationClient: FusedLocationProviderClient? = null
    var latitude = 0.0
    var longitude = 0.0
    var selectedMarkerLocation: LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(com.example.indianicpractical.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        binding.btnInfo.setOnClickListener {
            findNavController().navigate(R.id.action_mapFragment_to_weatherInfoFragment)
        }
        if (isLocationPermissionGranted()) {
            if (isLocationEnabled()) {
                getCurrentLocation()
            } else {
                Toast.makeText(activity, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)

            }

        }
    }


    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationClient?.lastLocation?.addOnSuccessListener { location ->
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
                val markerPosition = LatLng(latitude, longitude)

                setCurrentLocationMarker(markerPosition)
                setMarkerInCenterPoint()

                map?.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {

                    override fun onMarkerDrag(marker: Marker) {
                        selectedMarkerLocation = marker.position
                    }

                    override fun onMarkerDragEnd(p0: Marker) {

                    }

                    override fun onMarkerDragStart(p0: Marker) {

                    }
                })

            }
        }
        map?.setOnCameraMoveListener {
            selectedMarkerLocation = map?.cameraPosition?.target
            map?.clear()
            map?.addMarker(
                MarkerOptions().position(selectedMarkerLocation!!).title("Selected Location")
            )?.apply {
                isDraggable = true
            }
        }

    }

    private fun setMarkerInCenterPoint() {
        val centerPosition = map?.cameraPosition?.target
        map?.addMarker(MarkerOptions().position(centerPosition!!).title(""))

        map?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                centerPosition!!, AppConstant.MAP_ZOOM
            )
        )

    }

    private fun setCurrentLocationMarker(markerPosition: LatLng) {
        map?.addMarker(MarkerOptions().position(markerPosition))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

    }


    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ), AppConstant.REQUEST_CODE
                )
            }
            false
        } else {
            true
        }
    }

}