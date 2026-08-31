package br.edu.ifsp.hto.htoipdm.filmes.features.filmes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.hto.htoipdm.filmes.data.Filme
import br.edu.ifsp.hto.htoipdm.filmes.remote.filme.FilmeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFilmesViewModel @Inject constructor(
    private val filmeRepository: FilmeRepository
) : ViewModel() {

    private val _filmes = MutableStateFlow<List<Filme>>(emptyList())
    val filmes: StateFlow<List<Filme>> = _filmes

    init {
        listar()
    }

    private fun listar() {
        viewModelScope.launch {
            _filmes.value = filmeRepository.listar()
        }
    }
}