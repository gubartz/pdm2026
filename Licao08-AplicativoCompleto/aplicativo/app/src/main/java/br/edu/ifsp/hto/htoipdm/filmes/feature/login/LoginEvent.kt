package br.edu.ifsp.hto.htoipdm.filmes.feature.login

sealed interface LoginEvent {
    object NavigateToHome : LoginEvent
}