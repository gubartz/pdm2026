package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import br.edu.ifsp.hto.htoipdm.filmes.features.filmes.ListFilmesScreen
import br.edu.ifsp.hto.htoipdm.filmes.features.filmes.ListFilmesScreen2
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.LoginRoute

@Composable
fun MainNavigation(

) {
    val backStack = rememberNavBackStack(LoginRoute)

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()

        },
        entryProvider = { key ->
            when (key) {

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}