package br.edu.ifsp.hto.htoipdm.filmes.remote.interceptors

import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthRepository
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.LoginRepository
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.TokenManager
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