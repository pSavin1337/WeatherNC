package com.example.weathernc.domain.entity

sealed class Result {
    class Success<T>(val body: T) : Result()
    class Error(val message: String) : Result()
}
