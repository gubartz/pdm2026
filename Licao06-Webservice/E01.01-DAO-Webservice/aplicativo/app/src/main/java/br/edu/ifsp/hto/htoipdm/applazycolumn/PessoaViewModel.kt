package br.edu.ifsp.hto.htoipdm.applazycolumn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.ApiClient
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository.PessoaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class PessoaViewModel() : ViewModel() {

    private val repository =
        PessoaRepository(ApiClient.pessoaService)

    private val _pessoas = MutableStateFlow<List<Pessoa>>(emptyList())

    var idPessoaEdicao by mutableStateOf<Long?>(null)

    val modoEdicao: Boolean
        get() = idPessoaEdicao != null

    val pessoas = _pessoas.asStateFlow()

    var nome by mutableStateOf("")
        private set

    var dataNascimento by mutableStateOf<LocalDate?>(null)
        private set

    fun onNomeChange(valor: String) {
        nome = valor
    }

    fun onDataNascimentoChange(valor: LocalDate) {
        dataNascimento = valor
    }


    init {
        carregar()
    }

    fun carregar() {
        viewModelScope.launch {
            _pessoas.value = repository.listar()
        }
    }

    fun salvar() {
        viewModelScope.launch {
            dataNascimento?.let {
                repository.inserir(
                    nome = nome,
                    dataNascimento = it
                )
                carregar()
            } ?: return@launch
        }
    }

    fun atualizar() {
        viewModelScope.launch {

        }
    }

    fun remover(id: Long) {
        viewModelScope.launch {
            repository.remover(id)
            carregar()
        }
    }
}