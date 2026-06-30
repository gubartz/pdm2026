package br.edu.ifsp.hto.htoipdm.filmes.remote.auth

import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val auhService: AuthService,
    private val tokenManager: TokenManager
) {
    suspend fun login(
        login: String,
        senha: String
    ): Result<LoginResponse> {

        return try {

            val response = auhService.login(
                LoginRequest(login, senha)
            )

            if (response.isSuccessful) {

                val body = response.body()

                if (body != null) {
                    tokenManager.saveToken(body.token)

                    Result.success(body)
                } else {
                    Result.failure(Exception("Empty response"))
                }

            } else {

                Result.failure(
                    Exception("Invalid username or password")
                )

            }

        } catch (e: Exception) {

            Result.failure(e)

        }
    }
}