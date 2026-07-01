package br.edu.ifsp.hto.htoipdm.filmes.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.hto.htoipdm.filmes.UiEventManager
import br.edu.ifsp.hto.htoipdm.filmes.features.UiEvent
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthRepository
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authRepository: AuthRepository,
    private val uiEventManager: UiEventManager
) : ViewModel() {
    var usuario by mutableStateOf("")
        private set
    var senha by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()
    val events = _events.asSharedFlow()

    fun onLoginChange(login: String) {
        _uiState.update {
            it.copy(usuario = login)
        }
    }

    fun onSenhaChange(password: String) {
        _uiState.update {
            it.copy(senha = password)
        }
    }

    fun doLogin() {
        viewModelScope.launch {

            _uiState.update { it.copy(loading = true) }

            val result = loginRepository.login(
                _uiState.value.usuario,
                _uiState.value.senha
            )

            _uiState.update { it.copy(loading = false) }

            result.fold(
                onSuccess = { response ->
                    authRepository.login(response.token)
                },
                onFailure = {
                    uiEventManager.emit(
                        UiEvent.Snackbar(
                            it.message ?: "Login failed"
                        )
                    )
                }
            )
        }
    }
}