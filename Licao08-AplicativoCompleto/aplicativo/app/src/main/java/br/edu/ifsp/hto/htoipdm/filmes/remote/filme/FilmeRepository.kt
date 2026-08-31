package br.edu.ifsp.hto.htoipdm.filmes.remote.filme

import br.edu.ifsp.hto.htoipdm.filmes.data.Filme
import javax.inject.Inject

class FilmeRepository @Inject constructor(
    private val filmeService: FilmeService
) {
    suspend fun listar(): List<Filme> =
        filmeService.listar().dataResponse ?: emptyList()
}