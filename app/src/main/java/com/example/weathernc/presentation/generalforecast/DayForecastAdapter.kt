package com.example.weathernc.presentation.generalforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weathernc.databinding.LargeWeatherCardBinding
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.utils.toNormalDateFormat
import com.example.weathernc.utils.toNormalHumidityFormat
import com.example.weathernc.utils.toNormalSpeedFormat
import com.example.weathernc.utils.toNormalTemperatureFormat

class DayForecastAdapter : RecyclerView.Adapter<DayForecastAdapter.DayForecastViewHolder>() {

    var dayForecastList = listOf<WeatherDayModel>()
    var onWeatherCardClick: (weatherDay: WeatherDayModel) -> Unit = {}

    class DayForecastViewHolder(val binding: LargeWeatherCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastViewHolder {
        val binding = LargeWeatherCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DayForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayForecastViewHolder, position: Int) {
        with(holder.binding) {
            with(dayForecastList[position]) {
                val protocol = "https:/"
                Glide.with(weatherCardIcon)
                    .load(protocol + iconUrl)
                    .into(weatherCardIcon)
                weatherCardDate.text = date.toNormalDateFormat()
                weatherCardHumidity.text = humidity.toString().toNormalHumidityFormat()
                weatherCardWind.text = windSpeed.toString().toNormalSpeedFormat()
                weatherCardAverageTemperature.text =
                    averageTemperature.toString().toNormalTemperatureFormat()
                weatherCardMinTemperature.text =
                    minimalTemperature.toString().toNormalTemperatureFormat()
                weatherCardMaxTemperature.text =
                    maximumTemperature.toString().toNormalTemperatureFormat()
                weatherCardContent.setOnClickListener {
                    val generalToDetailAction =
                        GeneralForecastFragmentDirections.actionGeneralForecastFragmentToDetailForecastFragment()
                    holder.binding.root.findNavController().navigate(generalToDetailAction)
                    onWeatherCardClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int = dayForecastList.size

}
