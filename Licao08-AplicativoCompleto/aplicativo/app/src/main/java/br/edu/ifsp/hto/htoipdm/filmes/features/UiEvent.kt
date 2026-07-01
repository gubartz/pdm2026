package br.edu.ifsp.hto.htoipdm.filmes.features

sealed interface UiEvent {

    data class Snackbar(
        val message: String
    ) : UiEvent

    data class Toast(
        val message: String
    ) : UiEvent

    object Logout : UiEvent
}