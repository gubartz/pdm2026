package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import br.edu.ifsp.hto.htoipdm.filmes.feature.telaprincipal.TelaPrincipalScreen
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.TelaPrincipalRoute

@Composable
fun MainNavigation(

) {
    val backStack = rememberNavBackStack(TelaPrincipalRoute)

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = { key ->
            when (key) {
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