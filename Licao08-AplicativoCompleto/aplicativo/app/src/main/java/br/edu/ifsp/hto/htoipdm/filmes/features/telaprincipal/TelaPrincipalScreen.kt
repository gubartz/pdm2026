package br.edu.ifsp.hto.htoipdm.filmes.features.telaprincipal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Desk
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.rememberNavBackStack
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation.BottomNavKey
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation.MainNavigation
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation.TabNavigation

@Composable
fun TelaPrincipalScreen() {
    val filmesBackStack = rememberNavBackStack(BottomNavKey.ListFilmesRoutes)
    val filmes2BackStack = rememberNavBackStack(BottomNavKey.ListFilmesRoutes2)

    var currentTab by remember {
        mutableStateOf<BottomNavKey>(BottomNavKey.ListFilmesRoutes)
    }

    val currentBackStack = when (currentTab) {
        BottomNavKey.ListFilmesRoutes -> filmesBackStack
        BottomNavKey.ListFilmesRoutes2 -> filmes2BackStack
    }

    val items = listOf(
        BottomNavItem(
            route = BottomNavKey.ListFilmesRoutes,
            icon = Icons.Default.Movie,
            label = "Filmes"
        ),
        BottomNavItem(
            route = BottomNavKey.ListFilmesRoutes2,
            icon = Icons.Default.Desk,
            label = "Filmes"
        ),
        BottomNavItem(
            route = BottomNavKey.ListFilmesRoutes2,
            icon = Icons.Default.Logout,
            label = "Sair"
        )
    )

    val isAtHomeRoot = currentTab == BottomNavKey.ListFilmesRoutes && currentBackStack.size == 1

    BackHandler(enabled = !isAtHomeRoot) {
        when {
            currentBackStack.size > 1 -> {
                // still has screens pushed within this tab -> pop one
                currentBackStack.removeAt(currentBackStack.lastIndex)
            }

            currentTab != BottomNavKey.ListFilmesRoutes -> {
                // at tab root, but not on Home -> jump back to Home tab
                currentTab = BottomNavKey.ListFilmesRoutes
            }

            else -> {
                currentTab = BottomNavKey.ListFilmesRoutes
            }
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentTab == item.route,
                        onClick = {
                            currentTab = item.route
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(item.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .safeDrawingPadding()
        ) {
            TabNavigation(
                backStack = currentBackStack
            )
        }
    }
}

@Preview
@Composable
private fun TelaPrincipalScreenPreview() {
    TelaPrincipalScreen()
}