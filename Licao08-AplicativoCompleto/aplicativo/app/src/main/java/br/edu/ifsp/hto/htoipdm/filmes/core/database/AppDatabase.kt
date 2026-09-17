package br.edu.ifsp.hto.htoipdm.filmes.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.FilmeDAO
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.FilmeEntity
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.FilmeGeneroCrossRef
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.GeneroDAO
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.GeneroEntitiy

@Database(
    entities = [FilmeEntity::class,
        GeneroEntitiy::class,
        FilmeGeneroCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmeDao(): FilmeDAO
    abstract fun generoDao(): GeneroDAO
}