package br.edu.ifsp.hto.htoipdm.filmes.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthRepository
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val authState = authRepository.authState

    init {
        viewModelScope.launch {
            authRepository.initialize()
        }
    }
}