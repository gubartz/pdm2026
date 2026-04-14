package br.edu.ifsp.hto.htoipdm.applazycolumn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository.PessoaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class PessoaViewModel(private val pessoaRepository: PessoaRepository) : ViewModel() {

    val pessoas = pessoaRepository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun inserir(nome: String, dataNascimento: LocalDate) {
        viewModelScope.launch {
            pessoaRepository.inserir(nome, dataNascimento)
        }
    }

    fun atualizar(pessoa: Pessoa) {
        viewModelScope.launch {
            pessoaRepository.atualizar(pessoa)
        }
    }

    fun remover(id: Long) {
        viewModelScope.launch {
            pessoaRepository.remover(id)
        }
    }
}