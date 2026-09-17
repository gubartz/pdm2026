package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.GeneroEntitiy
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmeDAO {

    @Transaction
    @Query("SELECT * FROM filme")
    fun observarTodos(): Flow<List<FilmeComGeneros>>

    @Upsert
    suspend fun upsertAll(filmes: List<FilmeEntity>)

    @Query("DELETE FROM filme")
    suspend fun limparTudo()

    @Transaction
    suspend fun inserirCompleto(
        filme: FilmeEntity,
        generos: List<GeneroEntitiy>,
        relacionamentos: List<FilmeGeneroCrossRef>
    ) {
        upsert(filme)
        upsertGeneros(generos)
        upsertFilmeGeneros(relacionamentos)
    }

    @Transaction
    suspend fun sincronizar(
        filmes: List<FilmeEntity>,
        generos: List<GeneroEntitiy>,
        relacionamentos: List<FilmeGeneroCrossRef>
    ) {
        limparTudo()
        upsertAll(filmes)
        upsertGeneros(generos)
        upsertFilmeGeneros(relacionamentos)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(filme: FilmeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertGeneros(generos: List<GeneroEntitiy>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFilmeGeneros(
        relacionamentos: List<FilmeGeneroCrossRef>
    )
}