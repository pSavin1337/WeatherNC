package com.example.weathernc.presentation.detailforecast

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weathernc.databinding.FragmentDetailForecastBinding
import com.example.weathernc.databinding.LargeWeatherCardBinding
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.presentation.mainactivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailForecastFragment : Fragment() {

    private var _binding: FragmentDetailForecastBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailForecastViewModel by viewModels()
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
        val dayForecast = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(
                MainActivity.DAY_FORECAST_ARGUMENT_NAME,
                WeatherDayModel::class.java
            )
        } else {
            arguments?.getParcelable(
                MainActivity.DAY_FORECAST_ARGUMENT_NAME
            )
        }
        if (dayForecast != null) {
            setWeatherCard(binding.detailWeatherCard, dayForecast)
            viewModel.onViewCreated(dayForecast.city, dayForecast.date)
            viewModel.errorLiveData.observe(viewLifecycleOwner) { isError ->
                if (isError) {
                    onError()
                }
            }
            viewModel.hourForecastLiveData.observe(viewLifecycleOwner) { hourForecast ->
                with(binding.detailHourForecastRecyclerView) {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
                    hourAdapter.hourForecastList = hourForecast
                    adapter = hourAdapter
                }
            }
        } else {
            onError()
        }
    }

    private fun onError() = (activity as MainActivity).hideDetailForecast()

    private fun setWeatherCard(weatherCard: LargeWeatherCardBinding, content: WeatherDayModel) {
        Glide.with(requireContext()).load(content.iconUrl).into(weatherCard.weatherCardIcon)
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