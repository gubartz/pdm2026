package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFilmesViewModel @Inject constructor(
    private val filmeRepository: FilmeRepository
) : ViewModel() {

    val filmes: StateFlow<List<Filme>> = filmeRepository.filmes
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        viewModelScope.launch { filmeRepository.sync() }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            filmeRepository.sync()
            _isRefreshing.value = false
        }
    }
}