package com.example.indianicpractical.ui.weatherinfo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.indianicpractical.databinding.FragmentCurrentWeatherBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {
    lateinit var binding: FragmentCurrentWeatherBinding
    val weatherViewModel by activityViewModels<WeatherViewModel>()
    var weatherAdapter: WeatherAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.weatherData.observe(viewLifecycleOwner, Observer { forecastList ->
            weatherAdapter = WeatherAdapter(activity, forecastList.weather)
            binding.rvWeatherData.adapter = weatherAdapter
        })
    }

}