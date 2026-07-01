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
    val backStack = rememberNavBackStack(SplashRoute)

    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (authState) {
            AuthState.Authenticated -> {
                backStack.clear()
                backStack.add(TelaPrincipalRoute)
            }

            AuthState.Unauthenticated -> {
                backStack.clear()
                backStack.add(LoginRoute)
            }

            AuthState.Loading -> {}
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {

                is SplashRoute -> NavEntry(key) {
                    SplashScreen(
                        onAuthenticated = {
                            backStack.clear()
                            backStack.add(TelaPrincipalRoute)
                        },
                        onUnauthenticated = {
                            backStack.clear()
                            backStack.add(LoginRoute)
                        }
                    )
                }

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