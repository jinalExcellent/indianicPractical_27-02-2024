package com.example.indianicpractical.ui.weatherinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.indianicpractical.databinding.ItemWeatherDataBinding

class WeatherAdapter(activity: FragmentActivity?, var forecastList: List<Weather>) :
    RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: ItemWeatherDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(workOutBlock: Weather) {
            binding.weather = workOutBlock
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherAdapter.MyViewHolder {
        val binding =
            ItemWeatherDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.MyViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }
}