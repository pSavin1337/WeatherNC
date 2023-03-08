package com.example.weathernc.domain.entity

sealed class Result {
    class Success<T>(val body: T): Result()
    object Error : Result()
}
