package com.example.clima.repository

import android.util.Log
import android.widget.Toast
import com.example.clima.api.config.RetrofitConfig
import com.example.clima.api.model.ClimaReponse
import com.example.clima.api.service.ClimaApi
import com.example.clima.api.service.ClimaCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClimaRepository() {

    val retrofitClient = RetrofitConfig
        .getRetrofitInstance("https://api.weatherapi.com/v1/")
    val climaApi = retrofitClient.create(ClimaApi::class.java)


    fun getClima(local: String, listener: APIListner<ClimaReponse?>){
        val t = climaApi.clima(local)
        t.enqueue(object: Callback<ClimaReponse?> {
            override fun onResponse(call: Call<ClimaReponse?>, response: Response<ClimaReponse?>) {
                listener.sucesso(response.body())
            }

            override fun onFailure(call: Call<ClimaReponse?>, t: Throwable) {
                throw Exception("deu pau")
            }
        })
    }
}