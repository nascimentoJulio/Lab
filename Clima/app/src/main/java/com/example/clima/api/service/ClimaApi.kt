package com.example.clima.api.service

import com.example.clima.api.model.ClimaReponse
import retrofit2.Call
import retrofit2.http.*


interface ClimaApi {

    @Headers("key: 0d0c476f84254a8dba8191340211011")
    @GET("forecast.json?days=10")
    fun clima(@Query("q", encoded = true) local: String): Call<ClimaReponse?>
}