package com.example.weathernc.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathernc.Constants.DATABASE_VERSION
import com.example.weathernc.data.database.dao.WeatherDayDao
import com.example.weathernc.data.database.dao.WeatherHourDao
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.domain.entity.WeatherHourModel

@Database(
    entities = [WeatherDayModel::class, WeatherHourModel::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDayDao(): WeatherDayDao
    abstract fun weatherHourDao(): WeatherHourDao

}