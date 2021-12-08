package com.example.clima.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clima.api.model.ClimaReponse
import com.example.clima.api.service.ClimaCallback
import com.example.clima.repository.APIListner
import com.example.clima.repository.ClimaRepository
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    val repository = ClimaRepository()
    private val mClima = MutableLiveData<ClimaReponse?>()
    val clima: LiveData<ClimaReponse?> by lazy {
        mClima
    }

    fun getClima(local: String = "Porto Alegre") {
        val listener = object : APIListner<ClimaReponse?> {
            override fun sucesso(body: ClimaReponse?) {
                mClima.value = body
            }

            override fun erro(mensagem: String): String {
                TODO("Not yet implemented")
            }
        }
        viewModelScope.launch {
            repository.getClima(local, listener)
        }
    }
}