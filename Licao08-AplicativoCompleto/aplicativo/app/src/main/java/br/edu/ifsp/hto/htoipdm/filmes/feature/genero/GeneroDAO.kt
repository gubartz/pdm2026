package br.edu.ifsp.hto.htoipdm.filmes.feature.genero

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.FilmeEntity
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.FilmeGeneroCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface GeneroDAO {

    @Query("SELECT * FROM genero")
    fun listar(): Flow<List<GeneroEntitiy>>

    @Upsert
    suspend fun upsertAll(generos: List<GeneroEntitiy>)

    @Transaction
    suspend fun sincronizar(
        generos: List<GeneroEntitiy>,
    ) {
        limparTudo()
        upsertAll(generos)
    }

    @Query("DELETE FROM genero")
    suspend fun limparTudo()
}