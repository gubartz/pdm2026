package br.edu.ifsp.hto.htoipdm.applazycolumn.data.local

import androidx.compose.runtime.mutableStateListOf
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import java.time.LocalDate

class PessoaDAOImpl : PessoaDAO {

    private val pessoas = mutableStateListOf<Pessoa>()
    private var nextId = 1L

    override fun listar() = pessoas

    override fun inserir(nome: String, dataNascimento: LocalDate) {
        pessoas.add(Pessoa(nextId++, nome, dataNascimento))
    }

    override fun atualizar(pessoa: Pessoa) {
        val index = pessoas.indexOfFirst { it.id == pessoa.id }
        if (index != -1) {
            pessoas[index] = pessoa
        }
    }

    override fun remover(id: Long) {
        pessoas.removeAll { it.id == id }
    }


}