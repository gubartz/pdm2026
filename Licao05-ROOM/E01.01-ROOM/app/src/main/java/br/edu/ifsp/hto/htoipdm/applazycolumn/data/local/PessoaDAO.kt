package br.edu.ifsp.hto.htoipdm.applazycolumn.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.entity.PessoaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PessoaDAO {
    @Query("SELECT * FROM pessoa")

    fun listar(): Flow<List<PessoaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(pessoa: PessoaEntity)

    @Query("DELETE FROM pessoa WHERE id = :id")
    suspend fun remover(id: Long)
}