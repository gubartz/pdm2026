package br.edu.ifsp.hto.htoipdm.filmes.remote.auth

data class LoginResponse(
    val token: String,
    val exp: String
)
