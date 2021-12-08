package com.example.clima.repository

interface APIListner<T> {
    fun sucesso(body: T)
    fun erro(mensagem: String): String
}
