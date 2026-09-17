package br.edu.ifsp.hto.htoipdm.filmes.feature.telaprincipal

import androidx.compose.ui.graphics.vector.ImageVector
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation.BottomNavKey

data class BottomNavItem(
    val route: BottomNavKey,
    val icon: ImageVector,
    val label: String
)
