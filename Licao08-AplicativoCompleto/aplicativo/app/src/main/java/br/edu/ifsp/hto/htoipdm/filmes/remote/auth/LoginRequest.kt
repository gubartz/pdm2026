package br.edu.ifsp.hto.htoipdm.filmes.remote.auth

data class LoginRequest(
    val login: String,
    val senha: String
)
