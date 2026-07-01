package br.edu.ifsp.hto.htoipdm.filmes.remote

import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthRepository
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthService
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.TokenManager
import br.edu.ifsp.hto.htoipdm.filmes.remote.interceptors.AuthInterceptor
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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val BASE_URL = "http://10.0.2.2:5000/"

    @Provides
    @Named("login")
    fun provideLoginRetrofit(
        gson: Gson,
        @Named("loginClient") okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("api")
    fun provideApiRetrofit(
        @Named("authClient") okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        tokenManager: TokenManager,
        authRepository: AuthRepository
    ): AuthInterceptor {
        return AuthInterceptor(
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    @Named("authClient")
    fun provideOkHttpClientWithAuthInterceptor(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("loginClient")
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(
        @Named("login") retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}