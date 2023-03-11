package com.example.weathernc.presentation.generalforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathernc.R
import com.example.weathernc.databinding.FragmentGeneralForecastBinding
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.presentation.mainactivity.MainActivity
import com.example.weathernc.utils.toNormalCityFormat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GeneralForecastFragment : Fragment() {

    private var _binding: FragmentGeneralForecastBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GeneralForecastViewModel by viewModels()

    @Inject
    lateinit var dayAdapter: DayForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityName = if (viewModel.cityName == "") {
            getString(R.string.default_city)
        } else {
            viewModel.cityName
        }
        binding.generalCityEdittext.setText(cityName)
        binding.generalSearchButton.setOnClickListener {
            viewModel.cityName = binding.generalCityEdittext.text.toString().trim()
            viewModel.onSearchButtonClick()
        }
        viewModel.cityName = binding.generalCityEdittext.text.toString().trim()
        viewModel.onViewCreated()
        viewModel.dayForecastLiveData.observe(viewLifecycleOwner) { forecastDays ->
            binding.generalCityNameTextview.text = forecastDays[0].city.toNormalCityFormat()
            with(binding.generalRecyclerview) {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                dayAdapter.dayForecastList = forecastDays
                dayAdapter.onWeatherCardClick = ::onWeatherCardClick
                adapter = dayAdapter
            }
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                onError()
            }
        }
    }

    fun onWeatherCardClick(dayForecast: WeatherDayModel) {
        (activity as MainActivity).showDetailForecast(dayForecast)
    }

    private fun onError() =
        Toast.makeText(
            requireContext(),
            getText(R.string.city_not_found),
            Toast.LENGTH_SHORT
        ).show()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}