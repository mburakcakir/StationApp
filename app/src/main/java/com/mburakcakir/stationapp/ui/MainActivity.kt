package com.mburakcakir.stationapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mburakcakir.stationapp.R
import com.mburakcakir.stationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AsisCTStationApp)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}