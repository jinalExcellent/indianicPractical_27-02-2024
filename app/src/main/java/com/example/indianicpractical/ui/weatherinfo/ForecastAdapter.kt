package com.example.indianicpractical.ui.weatherinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.indianicpractical.databinding.ItemForecastDataBinding

class ForecastAdapter(activity: FragmentActivity?, var forecastList: List<Forecast>) :
    RecyclerView.Adapter<ForecastAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: ItemForecastDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(workOutBlock: Forecast) {
            binding.forecast = workOutBlock
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastAdapter.MyViewHolder {
        val binding =
            ItemForecastDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastAdapter.MyViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }
}