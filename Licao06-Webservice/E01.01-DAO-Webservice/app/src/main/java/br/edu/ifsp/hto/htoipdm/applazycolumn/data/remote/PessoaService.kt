package br.edu.ifsp.hto.htoipdm.applazycolumn.data.remote

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.ApiResponse
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PessoaService {
    @GET("pessoas")
    suspend fun getPessoas(): ApiResponse<List<Pessoa>>

    @POST("pessoa")
    suspend fun inserir(@Body pessoa: Pessoa): ApiResponse<Unit>

    @DELETE("pessoa/{id}")
    suspend fun remover(
        @Path("id") id: Long
    ): ApiResponse<Unit>
}