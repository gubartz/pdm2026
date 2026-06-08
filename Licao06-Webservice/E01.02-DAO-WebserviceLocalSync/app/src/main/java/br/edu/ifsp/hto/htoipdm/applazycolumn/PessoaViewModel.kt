package br.edu.ifsp.hto.htoipdm.applazycolumn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.remote.ApiClient
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository.PessoaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class PessoaViewModel(private val pessoaRepository: PessoaRepository) : ViewModel() {

    private val _pessoas = MutableStateFlow<List<Pessoa>>(emptyList())

    var idPessoaEdicao by mutableStateOf<Long?>(null)

    val modoEdicao: Boolean
        get() = idPessoaEdicao != null

    val pessoas = pessoaRepository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    var nome by mutableStateOf("")
        private set

    var dataNascimento by mutableStateOf<LocalDate?>(null)
        private set

    init {
        viewModelScope.launch {
            pessoaRepository.sincronizar()
        }
    }

    fun onNomeChange(valor: String) {
        nome = valor
    }

    fun onDataNascimentoChange(valor: LocalDate) {
        dataNascimento = valor
    }

    fun salvar() {
        viewModelScope.launch {
            val id = idPessoaEdicao ?: 0
            dataNascimento?.let {
                pessoaRepository.inserir(
                    id = id,
                    nome = nome,
                    dataNascimento = it
                )
            } ?: return@launch
        }
    }

    fun remover(id: Long) {
        viewModelScope.launch {
            try {
                pessoaRepository.remover(id)
                pessoaRepository.removerRemoto(id)
            } catch (e: Exception) {

            }
        }
    }
}