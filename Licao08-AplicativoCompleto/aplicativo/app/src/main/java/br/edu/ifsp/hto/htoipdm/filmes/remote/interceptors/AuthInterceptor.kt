package br.edu.ifsp.hto.htoipdm.filmes.remote.interceptors

import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .apply {
                tokenManager.getToken()?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }
            .build()

        return chain.proceed(request)
    }
}