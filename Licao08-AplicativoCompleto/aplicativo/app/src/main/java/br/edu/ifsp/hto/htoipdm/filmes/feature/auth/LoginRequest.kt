package br.edu.ifsp.hto.htoipdm.filmes.feature.auth

data class LoginRequest(
    val login: String,
    val senha: String
)
