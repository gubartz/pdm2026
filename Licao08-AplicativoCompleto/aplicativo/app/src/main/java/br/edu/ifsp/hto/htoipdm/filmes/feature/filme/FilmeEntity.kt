package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.Genero
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.GeneroEntitiy

@Entity(
    tableName = "filme"
)
data class FilmeEntity(
    @PrimaryKey val id: Long,
    val titulo: String,
    val tituloOriginal: String,
    val dataLancamento: String,
    val duracao: Long,
    val sinopse: String,
    val posterPath: String?,
    val syncedAt: Long
)