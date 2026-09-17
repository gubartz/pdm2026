package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.InserirFilmeScreen
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.ListFilmesScreen
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.ListFilmesScreen2


@Composable
fun TabNavigation(
    backStack: NavBackStack<NavKey>,
) {

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = { key ->
            when (key) {
                is BottomNavKey.ListFilmesRoutes -> NavEntry(key) {
                    ListFilmesScreen(onAdicionarFilme = { backStack.add(BottomNavKey.InserirFilmeRoute) })
                }

                is BottomNavKey.ListFilmesRoutes2 -> NavEntry(key) {
                    ListFilmesScreen2()
                }

                is BottomNavKey.InserirFilmeRoute -> NavEntry(key) {
                    InserirFilmeScreen(
                        onVoltar = {
                            backStack.removeLastOrNull()
                        }
                    )
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}