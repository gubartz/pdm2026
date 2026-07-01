package br.edu.ifsp.hto.htoipdm.filmes.features.login

data class LoginUiState(
    val usuario: String = "",
    val senha: String = "",
    val loading: Boolean = false
)