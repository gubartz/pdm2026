package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface BottomNavKey : NavKey {
    @Serializable
    data object ListFilmesRoutes : BottomNavKey

    @Serializable
    data object ListFilmesRoutes2 : BottomNavKey
}