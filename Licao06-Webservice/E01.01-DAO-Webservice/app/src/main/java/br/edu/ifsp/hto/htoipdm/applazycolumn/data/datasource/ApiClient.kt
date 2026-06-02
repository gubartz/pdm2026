package br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.remote.PessoaService
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.utils.LocalDateAdapter
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate


object ApiClient {
    val logging =
        HttpLoggingInterceptor().apply {
            level =
                HttpLoggingInterceptor.Level.BODY
        }

    private val gson =
        GsonBuilder()
            .registerTypeAdapter(
                LocalDate::class.java,
                LocalDateAdapter()
            )
            .create()

    val client =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000/")
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create(gson)
        )
        .build()

    val pessoaService: PessoaService =
        retrofit.create(PessoaService::class.java)
}