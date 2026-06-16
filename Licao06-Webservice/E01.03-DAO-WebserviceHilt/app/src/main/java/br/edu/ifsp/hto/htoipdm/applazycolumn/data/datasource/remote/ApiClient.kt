package br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.remote

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.remote.PessoaService
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.utils.LocalDateAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import javax.inject.Singleton

//TODO(12) Alterar para prover acesso ao webservice pelo hilt
@Module
@InstallIn(SingletonComponent::class)
object ApiClient {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(
                LocalDate::class.java,
                LocalDateAdapter()
            )
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
    }

    @Provides
    @Singleton
    fun providePessoaService(
        retrofit: Retrofit
    ): PessoaService {
        return retrofit.create(PessoaService::class.java)
    }
}