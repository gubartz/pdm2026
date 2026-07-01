package br.edu.ifsp.hto.htoipdm.filmes.features.login

sealed interface LoginEvent {
    object NavigateToHome : LoginEvent
}