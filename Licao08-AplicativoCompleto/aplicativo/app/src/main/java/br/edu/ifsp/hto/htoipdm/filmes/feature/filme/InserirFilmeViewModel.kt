package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.hto.htoipdm.filmes.UiEventManager
import br.edu.ifsp.hto.htoipdm.filmes.feature.UiEvent
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.Genero
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.GeneroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InserirFilmeViewModel @Inject constructor(
    private val filmeRepository: FilmeRepository,
    private val generoRepository: GeneroRepository,
    private val uiEventManager: UiEventManager
) : ViewModel() {

    val generos: StateFlow<List<Genero>> = generoRepository.generos
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private val _uiState = MutableStateFlow(InserirFilmeUiState())
    val uiState = _uiState.asStateFlow()

    private val _filmeSalvo = MutableSharedFlow<Unit>()
    val filmeSalvo = _filmeSalvo.asSharedFlow()

    fun onTituloChange(v: String) = _uiState.update { it.copy(titulo = v) }

    fun onTituloOriginalChange(v: String) = _uiState.update { it.copy(tituloOriginal = v) }
    fun onDataLancamentoChange(v: String) = _uiState.update { it.copy(dataLancamento = v) }
    fun onDuracaoChange(v: String) = _uiState.update { it.copy(duracao = v.filter(Char::isDigit)) }
    fun onSinopseChange(v: String) = _uiState.update { it.copy(sinopse = v) }

    init {
        viewModelScope.launch { generoRepository.sync() }
    }

    fun onGeneroChange(genero: Genero) {
        _uiState.update { state ->
            val selecionados = state.generosSelecionados

            if (genero in selecionados) {
                state.copy(
                    generosSelecionados = selecionados - genero
                )
            } else {
                state.copy(
                    generosSelecionados = selecionados + genero
                )
            }
        }
    }

    fun salvar() {
        val estado = _uiState.value

        if (estado.titulo.isBlank() || estado.duracao.isBlank()) {
            viewModelScope.launch {
                uiEventManager.emit(UiEvent.Snackbar("Preencha título e duração"))
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(salvando = true) }

            val request = FilmeRequest(
                titulo = estado.titulo,
                tituloOriginal = estado.tituloOriginal,
                dataLancamento = estado.dataLancamento,
                duracao = estado.duracao.toLong(),
                sinopse = estado.sinopse
            )

            val resultado = filmeRepository.inserir(request)

            _uiState.update { it.copy(salvando = false) }

            resultado.fold(
                onSuccess = { _filmeSalvo.emit(Unit) },
                onFailure = {
                    uiEventManager.emit(
                        UiEvent.Snackbar(it.message ?: "Erro ao salvar filme")
                    )
                }
            )
        }
    }

}