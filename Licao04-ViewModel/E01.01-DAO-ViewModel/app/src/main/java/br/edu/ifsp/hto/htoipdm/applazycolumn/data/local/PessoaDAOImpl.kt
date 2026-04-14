package br.edu.ifsp.hto.htoipdm.applazycolumn.data.local

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.PessoaDataSource
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import kotlinx.coroutines.flow.Flow
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
        _pessoas.value = _pessoas.value.map {
            if (it.id == pessoa.id) pessoa else it
        }
    }

    override suspend fun remover(id: Long) {
        _pessoas.value = _pessoas.value.filterNot { it.id == id }
    }


}