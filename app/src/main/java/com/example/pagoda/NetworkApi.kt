package com.example.pagoda

import com.example.pagoda.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET("forecast.json")
    fun getWeather(
        @Query("key")k:String,
        @Query("days")days: String,
        @Query("q")city:String,
        @Query("aqi")aqi:String
    ):Call<Weather>





}