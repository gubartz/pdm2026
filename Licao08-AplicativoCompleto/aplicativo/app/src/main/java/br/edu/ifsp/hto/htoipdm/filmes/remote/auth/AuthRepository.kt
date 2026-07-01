package br.edu.ifsp.hto.htoipdm.filmes.remote.auth

import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val tokenManager: TokenManager
) {
    private val _authState =
        MutableStateFlow<AuthState>(AuthState.Unauthenticated)

    val authState: StateFlow<AuthState> = _authState

    fun isLoggedIn(): Boolean {
        return _authState.value is AuthState.Authenticated
    }

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