package com.example.weathernc.di

import android.content.Context
import androidx.room.Room
import com.example.weathernc.Constants.WEATHER_DAY_TABLE_NAME
import com.example.weathernc.data.database.WeatherDatabase
import com.example.weathernc.data.database.dao.WeatherDayDao
import com.example.weathernc.data.database.dao.WeatherHourDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, WEATHER_DAY_TABLE_NAME)
            .build()
    }

    @Provides
    fun provideWeatherDayDao(database: WeatherDatabase): WeatherDayDao {
        return database.weatherDayDao()
    }

    @Provides
    fun provideWeatherHourDao(database: WeatherDatabase): WeatherHourDao {
        return database.weatherHourDao()
    }

}