package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import br.edu.ifsp.hto.htoipdm.filmes.features.login.LoginScreen

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(LoginRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is LoginRoute -> NavEntry(key) {
                    LoginScreen(
                        onLoginSuccess = {
                            backStack.add(TelaPrincipalRoute)
                        }
                    )
                }

                is TelaPrincipalRoute -> NavEntry(key) {


                }


                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}
