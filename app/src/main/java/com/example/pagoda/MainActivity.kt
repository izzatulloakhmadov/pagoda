package com.example.pagoda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.pagoda.databinding.ActivityMainBinding
import com.example.pagoda.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var weatherAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit=RetrofitHelper.getInstance()
        val call=retrofit.create(NetworkApi::class.java)
        weatherAdapter= WeatherAdapter()
        binding.recyclerview.adapter=weatherAdapter
        binding.btnSearch.setOnClickListener {
            call.getWeather(
                "894984c82a914c5b8ad64517221609  ",
                "3",
                binding.editSearch.text.toString(),
            "no"
            ).enqueue(object:   Callback<Weather>{
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    val imgCurrent:String="https:"+response.body()!!.current.condition.icon
                    Glide.with(this@MainActivity).load(imgCurrent).into(binding.imgCurret)
                    val tempCurrent:String=response.body()!!.current.tempC.toString()+"°C"
                    binding.tempCurrenta.text=tempCurrent
                    weatherAdapter.submitList(response.body()!!.forecast.forecastday)
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                }

            })
        }

    }
}