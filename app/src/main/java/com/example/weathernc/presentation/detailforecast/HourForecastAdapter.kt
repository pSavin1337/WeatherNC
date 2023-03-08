package com.example.weathernc.presentation.detailforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weathernc.databinding.SmallWeatherCardBinding
import com.example.weathernc.domain.entity.WeatherHourModel

class HourForecastAdapter : RecyclerView.Adapter<HourForecastAdapter.HourForecastViewHolder>() {

    var hourForecastList = listOf<WeatherHourModel>()

    class HourForecastViewHolder(val binding: SmallWeatherCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourForecastViewHolder {
        val binding = SmallWeatherCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HourForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourForecastViewHolder, position: Int) {
        with(holder){
            with(hourForecastList[position]) {
                val protocol = "https:/"
                Glide.with(holder.itemView.context)
                    .load(protocol + iconUrl)
                    .into(binding.smallWeatherIcon)
                binding.smallWeatherHour.text = hour
                binding.smallWeatherTemperature.text = temperature.toString()
            }
        }
    }

    override fun getItemCount(): Int = hourForecastList.size

}
