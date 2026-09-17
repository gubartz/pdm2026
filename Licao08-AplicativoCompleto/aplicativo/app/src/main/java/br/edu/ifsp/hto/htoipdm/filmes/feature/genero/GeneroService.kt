package br.edu.ifsp.hto.htoipdm.filmes.feature.genero

import br.edu.ifsp.hto.htoipdm.filmes.core.network.ApiResponse
import retrofit2.http.GET

interface GeneroService {

    @GET("generos")
    suspend fun listar(): ApiResponse<List<Genero>>
}