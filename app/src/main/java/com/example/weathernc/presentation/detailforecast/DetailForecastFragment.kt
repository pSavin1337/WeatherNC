package com.example.weathernc.presentation.detailforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weathernc.databinding.FragmentDetailForecastBinding
import com.example.weathernc.databinding.LargeWeatherCardBinding
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.presentation.viewmodels.ForecastViewModel
import com.example.weathernc.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailForecastFragment : Fragment() {

    private var _binding: FragmentDetailForecastBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ForecastViewModel by activityViewModels()

    @Inject
    lateinit var hourAdapter: HourForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentDayForecast.observe(viewLifecycleOwner) { dayForecast ->
            binding.detailCityNameTextview.text = dayForecast.city.toNormalCityFormat()
            setWeatherCard(binding.detailWeatherCard, dayForecast)
            viewModel.getHourForecast(dayForecast.city, dayForecast.date)
            viewModel.hourForecastLiveData.observe(viewLifecycleOwner) { hourForecast ->
                with(binding.detailHourForecastRecyclerView) {
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    hourAdapter.hourForecastList = hourForecast
                    adapter = hourAdapter
                }
            }
        }
    }

    private fun setWeatherCard(weatherCard: LargeWeatherCardBinding, content: WeatherDayModel) {
        val protocol = "https:/"
        Glide.with(this)
            .load(protocol + content.iconUrl)
            .into(weatherCard.weatherCardIcon)
        weatherCard.weatherCardDate.text = content.date.toNormalDateFormat()
        weatherCard.weatherCardHumidity.text = content.humidity.toString().toNormalHumidityFormat()
        weatherCard.weatherCardWind.text = content.windSpeed.toString().toNormalSpeedFormat()
        weatherCard.weatherCardAverageTemperature.text =
            content.averageTemperature.toString().toNormalTemperatureFormat()
        weatherCard.weatherCardMinTemperature.text =
            content.minimalTemperature.toString().toNormalTemperatureFormat()
        weatherCard.weatherCardMaxTemperature.text =
            content.maximumTemperature.toString().toNormalTemperatureFormat()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}