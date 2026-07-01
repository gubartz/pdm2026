package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import br.edu.ifsp.hto.htoipdm.filmes.features.filmes.ListFilmesScreen
import br.edu.ifsp.hto.htoipdm.filmes.features.telaprincipal.TelaPrincipalScreen
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthViewModel
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.AuthState
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.LoginRoute
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.TelaPrincipalRoute

@Composable
fun MainAppNavigation() {
    val backStack = rememberNavBackStack(ListFilmes)

    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (authState) {

            AuthState.Authenticated -> {
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

                is TelaPrincipalRoute -> NavEntry(key) {
                    TelaPrincipalScreen()
                }

                is ListFilmes -> NavEntry(key) {
                    ListFilmesScreen()
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}