package com.example.move_marker_master

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    fun mapInit(googleMap: GoogleMap){
        googleMap.apply {
            // just a random location our map will point to when its launched
            val klcc = LatLng(3.1579513, 101.7116233)
            addMarker(MarkerOptions().apply {
                position(klcc)
                title("Marker pointed at klcc")
                draggable(false)
            })
            // setup zoom level
            animateCamera(CameraUpdateFactory.newLatLngZoom(klcc,18f))

            // maps events we need to respond to
            googleMap.setOnCameraMoveListener {
                mMap.clear()
                // display imageView
                imgLocationPinUp?.visibility = View.VISIBLE
            }
            googleMap.setOnCameraIdleListener {
                imgLocationPinUp?.visibility = View.GONE
                // customizing map marker with a custom icon
                // and place it on the current map camera position
                val markerOptions = MarkerOptions().position(mMap.cameraPosition.target)

                mMap.addMarker(markerOptions)
            }

        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mapInit(googleMap)

    }
}
