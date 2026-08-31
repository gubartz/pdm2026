package br.edu.ifsp.hto.htoipdm.filmes.data

data class Filme(
    val id: Long,
    val titulo: String,
    val data_lancamento: String,
    val duracao: Long,
    val sinopse: String
)
