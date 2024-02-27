package com.example.indianicpractical.ui.weatherinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.indianicpractical.databinding.FragmentWeatherInforagmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherInfoFragment : Fragment() {
    var binding: FragmentWeatherInforagmentBinding? = null
    var viewAdapter: WeatherPagerAdapter? = null
    val weatherViewModel by activityViewModels<WeatherViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeatherInforagmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*get weather data*/
        weatherViewModel.getWeather("Ahmedabad")
        weatherViewModel.getForecastInfo("Ahmedabad")
        viewAdapter = activity?.supportFragmentManager?.let { WeatherPagerAdapter(it) }
        binding?.viewPager?.adapter = viewAdapter

        binding?.tabLayout?.setupWithViewPager(binding?.viewPager)


    }

}