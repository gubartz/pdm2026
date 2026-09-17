package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.Genero

data class InserirFilmeUiState(
    val titulo: String = "",
    val tituloOriginal: String = "",
    val dataLancamento: String = "",
    val duracao: String = "",
    val sinopse: String = "",
    val generosSelecionados: List<Genero> = emptyList(),
    val salvando: Boolean = false
)
