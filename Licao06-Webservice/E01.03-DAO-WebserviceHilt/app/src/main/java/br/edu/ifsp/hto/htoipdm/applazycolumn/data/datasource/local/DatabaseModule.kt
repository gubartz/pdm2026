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

//TODO(9) Criar um módulo do Banco de Dados locais, para que seja provê-lo por meio de injeção de
//  dependência (DI). São as linhas 15 e 16
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    //TODO(10) Adicionar as annotations Provides e Singleton
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

    //TODO(11) O provides permite injetar o DAO quando necessário.
    @Provides
    fun providePessoaDAO(
        database: AppDatabase
    ): PessoaDAO {
        return database.pessoaDAO()
    }
}