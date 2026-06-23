package com.example.navegacaotelas.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

//TODO(5) Criar um data object referente à rota da tela principal. Não esquecer de usar o
//  @Serializable
@Serializable
data object TelaPrincipalRoute : NavKey

//TODO(6) Para a rota do resultado utilizamos uma data class, pois ela vai receber dois parâmetros.
@Serializable
data class ResultadoSomaRoute(val numero1: Long, val numero2: Long) : NavKey