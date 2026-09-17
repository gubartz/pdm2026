package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.GeneroEntitiy

@Entity(
    tableName = "filme_genero",
    primaryKeys = ["filmeId", "generoId"],
    foreignKeys = [
        ForeignKey(
            entity = FilmeEntity::class,
            parentColumns = ["id"],
            childColumns = ["filmeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GeneroEntitiy::class,
            parentColumns = ["id"],
            childColumns = ["generoId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("generoId")
    ]
)
data class FilmeGeneroCrossRef(
    val filmeId: Long,
    val generoId: Long
)
