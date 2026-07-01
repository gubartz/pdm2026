package br.edu.ifsp.hto.htoipdm.filmes.remote.auth

import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val authService: AuthService
) {
    suspend fun login(
        usuario: String,
        senha: String
    ): Result<LoginResponse> {

        return try {

            val response = authService.login(
                LoginRequest(usuario, senha)
            )

            if (response.isSuccessful) {

                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty response"))

            } else {
                Result.failure(Exception("Invalid username or password"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}