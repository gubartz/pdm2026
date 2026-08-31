package br.edu.ifsp.hto.htoipdm.filmes.features.telaprincipal

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation.BottomNavKey

data class BottomNavItem(
    val route: BottomNavKey,
    val icon: ImageVector,
    val label: String
)
