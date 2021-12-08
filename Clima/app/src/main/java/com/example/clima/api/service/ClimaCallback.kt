package com.example.clima.api.service

import com.example.clima.api.config.RetrofitConfig

class ClimaCallback {
    companion object Clima {

        fun getApiClient(): ClimaApi {
            val retrofitClient = RetrofitConfig
                .getRetrofitInstance("https://api.weatherapi.com/v1/")
            return retrofitClient.create(ClimaApi::class.java)
        }

    }
}