package br.edu.ifsp.hto.htoipdm.filmes.feature.auth

data class LoginResponse(
    val token: String,
    val exp: String
)
