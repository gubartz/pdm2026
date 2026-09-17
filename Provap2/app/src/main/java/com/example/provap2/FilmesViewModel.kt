package com.example.provap2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.provap2.data.model.Filme
import com.example.provap2.data.repository.FilmeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmesViewModel : ViewModel() {
    // TODO(4) Declarar um atributo para o FilmeRepository
    val filmeRepository = FilmeRepository()

    // TODO(5) Usar um MutableStateFlow para observar o retorno do webservice. Ele deve começar como
    //  uma lista vazia.
    private val _filmes = MutableStateFlow<List<Filme>>(emptyList())
    val filmes = _filmes.asStateFlow()


    init {
        carregarFilmes()
    }

    // TODO(6) Chamar o método do FilmeRepository para listar os dados. Utilizar o viewModelScope, pois é uma tarefa assíncrona.
    fun carregarFilmes() {
        viewModelScope.launch {
            _filmes.value = filmeRepository.getFilmes()
        }

    }
}