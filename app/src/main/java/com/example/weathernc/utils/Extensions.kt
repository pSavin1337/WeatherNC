package com.example.weathernc.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toNormalDateFormat(): String {
    val input = SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
    val output = SimpleDateFormat("d MMMM", Locale("ru"))
    val getAbbreviate = input.parse(this) ?: throw Exception()
    return output.format(getAbbreviate)
}

fun String.toNormalTemperatureFormat(): String = "$this °C"

fun String.toNormalHumidityFormat(): String = "$this %"

fun String.toNormalSpeedFormat(): String = "$this км/ч"

fun String.toNormalCityFormat(): String {
    val indexOfFirst = 1
    return this.first().uppercase() + this.drop(indexOfFirst)
}
