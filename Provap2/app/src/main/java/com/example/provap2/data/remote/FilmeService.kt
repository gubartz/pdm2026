package com.example.provap2.data.remote

import com.example.provap2.data.model.Filme
import retrofit2.http.GET

interface FilmeService {
    // TODO(2) Declarar um método para a rota filmes que recupera os filmes usando o webservice
    @GET("filmes")
    suspend fun getFilmes(): List<Filme>
}