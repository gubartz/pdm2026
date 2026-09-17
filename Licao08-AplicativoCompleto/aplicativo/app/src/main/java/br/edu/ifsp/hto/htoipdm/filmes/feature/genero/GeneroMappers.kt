package br.edu.ifsp.hto.htoipdm.filmes.feature.genero

fun GeneroEntitiy.toDomain() = Genero(
    id = id,
    descricao = descricao
)

fun Genero.toEntity() = GeneroEntitiy(
    id = id,
    descricao = descricao
)