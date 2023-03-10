package com.example.weathernc.di

import com.example.weathernc.Constants
import com.example.weathernc.Constants.CONNECTION_TIME
import com.example.weathernc.data.network.WeatherApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(converterFactory: GsonConverterFactory, client: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create()
    }

    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideClient() = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", Constants.API_KEY)
                .addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .build()
            chain.proceed(newRequest)
        }
        .build()

}