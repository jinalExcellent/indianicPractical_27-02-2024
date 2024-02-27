package com.example.indianicpractical.ui.weatherinfo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.indianicpractical.R
import com.example.indianicpractical.databinding.FragmentFiveDaysForecastBinding
import com.google.gson.Gson

class FiveDaysForecastFragment : Fragment() {
    var binding: FragmentFiveDaysForecastBinding? = null
    val weatherViewModel by activityViewModels<WeatherViewModel>()
    var forecastAdapter: ForecastAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFiveDaysForecastBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.forecastData.observe(viewLifecycleOwner, Observer { forecastList ->
            forecastAdapter = ForecastAdapter(activity, forecastList.list)
            binding?.rvForecastData?.adapter = forecastAdapter
        })

    }

}