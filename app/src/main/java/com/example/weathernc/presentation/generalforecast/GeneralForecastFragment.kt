package com.example.weathernc.presentation.generalforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathernc.databinding.FragmentGeneralForecastBinding

class GeneralForecastFragment : Fragment() {

    private var _binding: FragmentGeneralForecastBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Todo default city

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}