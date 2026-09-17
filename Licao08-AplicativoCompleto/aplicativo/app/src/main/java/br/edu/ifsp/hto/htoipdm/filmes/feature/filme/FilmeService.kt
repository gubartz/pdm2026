package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import br.edu.ifsp.hto.htoipdm.filmes.core.network.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FilmeService {

    @GET("filmes")
    suspend fun listar(): ApiResponse<List<Filme>>

    @POST("filme")
    suspend fun inserir(@Body request: FilmeRequest): ApiResponse<Filme>
}