package br.edu.ifsp.hto.htoipdm.filmes.feature.login

data class LoginUiState(
    val usuario: String = "",
    val senha: String = "",
    val loading: Boolean = false
)