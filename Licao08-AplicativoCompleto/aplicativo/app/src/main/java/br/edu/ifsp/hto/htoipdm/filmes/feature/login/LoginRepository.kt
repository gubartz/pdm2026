package br.edu.ifsp.hto.htoipdm.filmes.feature.login

import br.edu.ifsp.hto.htoipdm.filmes.feature.auth.AuthService
import br.edu.ifsp.hto.htoipdm.filmes.feature.auth.LoginRequest
import br.edu.ifsp.hto.htoipdm.filmes.feature.auth.LoginResponse
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
                } ?: Result.failure(Exception("Resposta Vazia"))

            } else {
                Result.failure(Exception("Usuário ou senha Incorretos"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}