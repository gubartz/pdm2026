package br.edu.ifsp.hto.htoipdm.filmes.core.network

import br.edu.ifsp.hto.htoipdm.filmes.feature.auth.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authRepository: AuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .apply {
                authRepository.getToken()?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }
            .build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            runBlocking {
                authRepository.logout()
            }
        }

        return response
    }
}