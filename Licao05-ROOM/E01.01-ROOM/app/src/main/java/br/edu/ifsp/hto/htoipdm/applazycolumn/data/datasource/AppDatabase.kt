package br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.local.PessoaDAO
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.entity.PessoaEntity
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.utils.Converters

@Database(
    entities = [PessoaEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun pessoaDAO(): PessoaDAO
}