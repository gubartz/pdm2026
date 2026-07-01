package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation

sealed interface AuthState {
    data object Authenticated : AuthState
    data object Unauthenticated : AuthState
    data object Loading : AuthState
}