package com.example.navegacaotelas.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object TelaPrincipalRoute : NavKey

@Serializable
data class ResultadoSomaRoute(val numero1: Long, val numero2: Long) : NavKey