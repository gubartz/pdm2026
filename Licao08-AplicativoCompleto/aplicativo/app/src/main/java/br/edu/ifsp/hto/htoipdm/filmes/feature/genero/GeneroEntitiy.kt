package br.edu.ifsp.hto.htoipdm.filmes.feature.genero

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genero")
data class GeneroEntitiy(
    @PrimaryKey
    val id: Long,
    val descricao: String
)