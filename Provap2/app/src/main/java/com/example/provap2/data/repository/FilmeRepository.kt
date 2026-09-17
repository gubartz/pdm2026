package com.example.provap2.data.repository

import com.example.provap2.data.model.Filme
import com.example.provap2.data.remote.RetrofitClient

class FilmeRepository {
    // TODO(3) Declarar um metodo que chama o webservice. Utilizar RetrofitClient.filmeService juntamente com o método que você declarou no FilmeService.
    suspend fun getFilmes(): List<Filme> = RetrofitClient.filmeService.getFilmes()
}