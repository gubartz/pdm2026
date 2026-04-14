package br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.local.PessoaDAO
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import java.time.LocalDate

class PessoaRepository(
    private val pessoaDAO: PessoaDAO
) {
    fun listar() = pessoaDAO.listar()

    suspend fun inserir(nome: String, dataNascimento: LocalDate) {
        pessoaDAO.inserir(nome, dataNascimento)
    }

    suspend fun atualizar(pessoa: Pessoa) {
        pessoaDAO.atualizar(pessoa)
    }

    suspend fun remover(id: Long) {
        pessoaDAO.remover(id)
    }
}