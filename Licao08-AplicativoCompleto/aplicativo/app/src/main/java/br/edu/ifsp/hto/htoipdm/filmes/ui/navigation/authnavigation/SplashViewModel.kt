package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation

import androidx.lifecycle.ViewModel
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {

    suspend fun hasToken(): Boolean {
        return tokenManager.token.first() != null
    }
}