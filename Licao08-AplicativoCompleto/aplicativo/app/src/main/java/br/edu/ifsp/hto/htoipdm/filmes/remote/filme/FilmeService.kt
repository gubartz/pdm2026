package br.edu.ifsp.hto.htoipdm.filmes.remote.filme

import br.edu.ifsp.hto.htoipdm.filmes.data.Filme
import retrofit2.http.GET

interface FilmeService {

    @GET("filmes")
    suspend fun listar(): ApiResponse<List<Filme>>
}