package com.example.provap2.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // TODO(1) Colocar no BASE_URL o https://mpd492dc3879aed1db3e.free.beeceptor.com/
    val BASE_URL = "https://mpd492dc3879aed1db3e.free.beeceptor.com/"


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val filmeService: FilmeService = retrofit.create(FilmeService::class.java)
}