package br.edu.ifsp.hto.htoipdm.applazycolumn.data.local

import androidx.compose.runtime.mutableStateListOf
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.PessoaDataSource
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class PessoaDAOImpl : PessoaDAO {

    private val _pessoas = PessoaDataSource._pessoas
    val pessoas: StateFlow<List<Pessoa>> = _pessoas.asStateFlow()

    private var nextId = 1L

    override fun listar(): Flow<List<Pessoa>> = _pessoas

    override suspend fun inserir(nome: String, dataNascimento: LocalDate) {
        _pessoas.value += Pessoa(nextId++, nome, dataNascimento)
    }

    override suspend fun atualizar(pessoa: Pessoa) {
        // Precisa reatribuir a lista toda para notificar os observadores
        _pessoas.value = _pessoas.value.map {
            if (it.id == pessoa.id) pessoa else it
        }
    }

    override suspend fun remover(id: Long) {
        _pessoas.value = _pessoas.value.filterNot { it.id == id }
    }


}