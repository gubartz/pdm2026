package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import br.edu.ifsp.hto.htoipdm.filmes.features.login.LoginScreen
import br.edu.ifsp.hto.htoipdm.filmes.features.telaprincipal.TelaPrincipalScreen
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthViewModel

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(LoginRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {

                is LoginRoute -> NavEntry(key) {
                    LoginScreen()
                }

                is TelaPrincipalRoute -> NavEntry(key) {
                    TelaPrincipalScreen()
                }


                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}