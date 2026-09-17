package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.Genero

fun FilmeEntity.toDomain(generos: List<Genero>) = Filme(
    id = id,
    titulo = titulo,
    tituloOriginal = tituloOriginal,
    dataLancamento = dataLancamento,
    duracao = duracao,
    sinopse = sinopse,
    posterPath = posterPath,
    generos = generos
)

fun Filme.toEntity(
    syncedAt: Long = System.currentTimeMillis()
) = FilmeEntity(
    id = id,
    titulo = titulo,
    tituloOriginal = tituloOriginal,
    dataLancamento = dataLancamento,
    duracao = duracao,
    sinopse = sinopse,
    posterPath = posterPath,
    syncedAt = syncedAt
)

fun Filme.toGeneroCrossRefs() = generos.map { genero ->
    FilmeGeneroCrossRef(
        filmeId = id,
        generoId = genero.id
    )
}