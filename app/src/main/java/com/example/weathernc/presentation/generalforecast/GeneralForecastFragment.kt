package com.example.weathernc.presentation.generalforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.weathernc.R
import com.example.weathernc.databinding.FragmentGeneralForecastBinding
import com.example.weathernc.databinding.LargeWeatherCardBinding
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.presentation.mainactivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneralForecastFragment : Fragment() {

    private var _binding: FragmentGeneralForecastBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GeneralForecastViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.generalSearchButton.setOnClickListener {
            viewModel.onSearchButtonClick(binding.generalCityEdittext.text.toString().trim())
        }
        viewModel.onViewCreated(binding.generalCityEdittext.text.toString().trim())
        viewModel.dayForecastLiveData.observe(viewLifecycleOwner) { forecastThreeDays ->
            val firstDayForecast = forecastThreeDays[0]
            val secondDayForecast = forecastThreeDays[1]
            val thirdDayForecast = forecastThreeDays[2]
            setWeatherCard(binding.generalFirstWeatherCard, firstDayForecast)
            setWeatherCard(binding.generalSecondWeatherCard, secondDayForecast)
            setWeatherCard(binding.generalThirdWeatherCard, thirdDayForecast)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                onError()
            }
        }
    }

    private fun onError() =
        Toast.makeText(
            requireContext(),
            getText(R.string.city_not_found),
            Toast.LENGTH_SHORT
        ).show()

    private fun setWeatherCard(weatherCard: LargeWeatherCardBinding, content: WeatherDayModel) {
        val protocol = "https:/"
        Glide.with(this)
            .load(protocol + content.iconUrl)
            .into(weatherCard.weatherCardIcon)
        weatherCard.weatherCardDate.text = content.date
        weatherCard.weatherCardHumidity.text = content.humidity.toString()
        weatherCard.weatherCardWind.text = content.windSpeed.toString()
        weatherCard.weatherCardAverageTemperature.text = content.averageTemperature.toString()
        weatherCard.weatherCardMinTemperature.text = content.minimalTemperature.toString()
        weatherCard.weatherCardMaxTemperature.text = content.maximumTemperature.toString()
        weatherCard.weatherCardContent.setOnClickListener {
            (activity as MainActivity).showDetailForecast(content)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}