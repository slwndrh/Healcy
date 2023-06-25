package com.example.healcy.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healcy.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.healcy.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.hospital)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        val ZOOM_LEVEL = 11f

        val mlg = LatLng(-7.9786454,112.631783)
        mMap.addMarker(MarkerOptions().position(mlg))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mlg, ZOOM_LEVEL))

        val panti = LatLng(-7.9943067, 112.6314832)
        mMap.addMarker(MarkerOptions().position(panti).title("Panti Nirmala Hospital")
            .snippet("Jl. Kebalen Wetan No.2-8, Kotalama, Kedungkandang Sub-District, Malang City, East Java 65134"))

        val rssa = LatLng(-7.9723519, 112.628834)
        mMap.addMarker(MarkerOptions().position(rssa).title("RSUD Dr. Saiful Anwar")
            .snippet("Jl. Jaksa Agung Suprapto No.2, Klojen, Klojen Sub-District, Malang City, East Java 65112"))

        val lavalette = LatLng(-7.9657494, 112.6353458)
        mMap.addMarker(MarkerOptions().position(lavalette).title("Lavalette Hospital")
            .snippet("Jl. W.R. Supratman No.10, Rampal Celaket, Klojen Sub-District, Malang City, East Java 65111"))

        val unisma = LatLng(-7.9402573, 112.6062551)
        mMap.addMarker(MarkerOptions().position(unisma).title("Unisma Islamic Hospital")
            .snippet("Jl. Mayjen Haryono No.139, Dinoyo, Lowokwaru Sub-District, Malang City, East Java 65144"))

        val prima = LatLng(-7.9078937, 112.6550157)
        mMap.addMarker(MarkerOptions().position(prima).title("Prima Husada Hospital")
            .snippet("Jl. Raya Mondoroko, Mondoroko, Banjararum, Singosari Sub-District, Kabupaten Malang, Jawa Timur 65153"))

        val puri = LatLng(-7.9587592, 112.6529577)
        mMap.addMarker(MarkerOptions().position(puri).title("Puri Bunda Maternity and Pediatric Hospital")
            .snippet("Jl. Simpang Sulfat Utara No.60 A, Pandanwangi, Blimbing District, Malang City, East Java 65126"))

        val permata = LatLng(-7.938858, 112.6221088)
        mMap.addMarker(MarkerOptions().position(permata).title("Permata Hati Maternity and Pediatric Hospital")
            .snippet("Jl. Danau Toba, Lesanpuro, Kedungkandang Sub-District, Malang City, East Java 65138"))

        val aisiyah = LatLng(-7.988681, 112.6228727)
        mMap.addMarker(MarkerOptions().position(aisiyah).title("Aisyiyah Islamic Hospital")
            .snippet("Jl. Sulawesi No.16, Kasin, Klojen Sub-District, Malang City, East Java 65117"))

        val refa = LatLng(-8.0357588, 112.6400696)
        mMap.addMarker(MarkerOptions().position(refa).title("Refa Husada Maternity and Pediatric Hospital")
            .snippet("Jl. Tlogowaru, Kedungkandang Sub-District, Kota Malang, Jawa Timur 65132"))

        val pantiWaluya = LatLng(-7.9856253, 112.6226776)
        mMap.addMarker(MarkerOptions().position(pantiWaluya).title("Panti Waluya Sawahan Hospital")
            .snippet("Jl. Nusakambangan No.56, Kasin, Klojen Sub-District, Malang City, East Java 65117"))

        val bhayangkara = LatLng(-7.8717077, 112.5214411)
        mMap.addMarker(MarkerOptions().position(bhayangkara).title("Bhayangkara Hasta Brata Hospital")
            .snippet("Jl. Ngaglik, Batu Sub-District, Batu City, East Java 65311"))

        val prasetya = LatLng(-7.9047265, 112.6061162)
        mMap.addMarker(MarkerOptions().position(prasetya).title("Prasetya Husada Hospital")
            .snippet("Jl. Raya Ngijo Karangploso No.25, Kendalsari, Ngijo, Karang Ploso Sub-District, Kabupaten Malang, Jawa Timur 65152"))

        val ub = LatLng(-7.9412275, 112.6189614)
        mMap.addMarker(MarkerOptions().position(ub).title("Universitas Brawijaya Hospital")
            .snippet("Jl. Soekarno - Hatta, Lowokwaru, Lowokwaru Sub-District, Malang City, East Java 65141"))

        val husada = LatLng(-7.9682854, 112.6202424)
        mMap.addMarker(MarkerOptions().position(husada).title("Husada Bunda Maternity and Pediatric Hospital")
            .snippet("Jl. Pahlawan Trip, Oro-oro Dowo, Klojen, Malang City, East Java 65112"))

        val mutiara = LatLng(-7.9533696, 112.6384681)
        mMap.addMarker(MarkerOptions().position(mutiara).title("Mutiara Bunda Maternity and Pediatric Hospital")
            .snippet("Jl. Ciujung No.19, Purwantoro, Blimbing Sub-District, Malang City, East Java 65126"))

        val baptis = LatLng(-7.9063862, 112.5356191)
        mMap.addMarker(MarkerOptions().position(baptis).title("Baptis Hospital")
            .snippet("Jl. Raya P. Sudirman No.33, Tlekung, Junrejo Sub-District, Batu City, East Java 65314"))

        val karsa = LatLng(-7.872318, 112.5208741)
        mMap.addMarker(MarkerOptions().position(karsa).title("RSUD Karsa Husada Batu")
            .snippet("Jl. Ahmad Yani No.11-13, Ngaglik, Batu Sub-District, Batu City, East Java 65311"))
    }
}