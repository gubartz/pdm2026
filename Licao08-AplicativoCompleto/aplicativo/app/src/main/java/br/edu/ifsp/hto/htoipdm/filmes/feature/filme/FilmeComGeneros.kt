package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.GeneroEntitiy

data class FilmeComGeneros(
    @Embedded
    val filme: FilmeEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = FilmeGeneroCrossRef::class,
            parentColumn = "filmeId",
            entityColumn = "generoId"
        )
    )
    val generos: List<GeneroEntitiy>
)