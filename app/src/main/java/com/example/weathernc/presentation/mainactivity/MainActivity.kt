package com.example.weathernc.presentation.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.weathernc.R
import com.example.weathernc.databinding.ActivityMainBinding
import com.example.weathernc.domain.entity.WeatherDayModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun showDetailForecast(dayForecast: WeatherDayModel) {
        navController.navigate(
            R.id.action_generalForecastFragment_to_detailForecastFragment,
            bundleOf(
                DAY_FORECAST_ARGUMENT_NAME to dayForecast
            )
        )
    }

    fun hideDetailForecast() {
        navController.navigateUp()
    }

    companion object {
        const val DAY_FORECAST_ARGUMENT_NAME = "DAY_FORECAST"
    }
}