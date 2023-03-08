package com.example.weathernc.domain.usecases

import com.example.weathernc.domain.entity.Result

interface UseCase {

    suspend fun execute(): Result

}