package br.edu.ifsp.hto.htoipdm.applazycolumn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository.PessoaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class PessoaViewModel(private val pessoaRepository: PessoaRepository) : ViewModel() {
    var idPessoaEdicao by mutableStateOf<Long?>(null)
    var nome by mutableStateOf("")
        private set

    var dataNascimento by mutableStateOf<LocalDate?>(null)
        private set

    val modoEdicao: Boolean
        get() = idPessoaEdicao != null

    val pessoas = pessoaRepository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onNomeChange(valor: String) {
        nome = valor
    }

    fun onDataNascimentoChange(valor: LocalDate) {
        dataNascimento = valor
    }

    fun salvar() {
        viewModelScope.launch {
            dataNascimento?.let {
                pessoaRepository.inserir(
                    id = idPessoaEdicao,
                    nome = nome,
                    dataNascimento = it
                )
            }
            idPessoaEdicao = null
            nome = ""
            dataNascimento = null
        }
    }

    fun remover(id: Long) {
        viewModelScope.launch {
            pessoaRepository.remover(id)
        }
    }
}