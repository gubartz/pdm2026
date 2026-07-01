package br.edu.ifsp.hto.htoipdm.filmes.remote.auth

import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val tokenManager: TokenManager
) {
    private val _authState =
        MutableStateFlow<AuthState>(AuthState.Loading)

    val authState: StateFlow<AuthState> = _authState

    suspend fun login(token: String) {
        tokenManager.saveToken(token)
        _authState.value = AuthState.Authenticated
    }

    fun getToken(): String? = tokenManager.getToken()

    suspend fun logout() {
        _authState.value = AuthState.Unauthenticated
        tokenManager.clear()
    }

    suspend fun initialize() {
        _authState.value =
            if (tokenManager.token.first() != null)
                AuthState.Authenticated
            else
                AuthState.Unauthenticated
    }
}