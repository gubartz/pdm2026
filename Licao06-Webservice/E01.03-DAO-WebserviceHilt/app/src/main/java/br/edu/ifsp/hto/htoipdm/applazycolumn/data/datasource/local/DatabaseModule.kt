package br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.local

import android.content.Context
import androidx.room.Room
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.local.PessoaDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun providePessoaDAO(
        database: AppDatabase
    ): PessoaDAO {
        return database.pessoaDAO()
    }
}