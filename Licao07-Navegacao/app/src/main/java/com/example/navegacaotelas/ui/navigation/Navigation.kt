package com.example.navegacaotelas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navegacaotelas.ResultadoSomaScreen
import com.example.navegacaotelas.TelaPrincipalScreen

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(TelaPrincipalRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is TelaPrincipalRoute -> NavEntry(key) {
                    TelaPrincipalScreen(
                        onNavigateResultadoSoma = { n1, n2 ->
                            backStack.add(
                                ResultadoSomaRoute(
                                    numero1 = n1,
                                    numero2 = n2
                                )
                            )
                        }
                    )
                }

                is ResultadoSomaRoute -> NavEntry(key) {
                    ResultadoSomaScreen(numero1 = key.numero1, numero2 = key.numero2)
                }


                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}
