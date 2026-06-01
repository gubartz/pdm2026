package br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.local.PessoaDAO
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.entity.PessoaEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class PessoaRepository(
    private val pessoaDAO: PessoaDAO
) {
    fun listar(): Flow<List<Pessoa>> {
        return pessoaDAO.listar().map { pessoaEntities ->
            pessoaEntities.map { pessoaEntity ->
                Pessoa(
                    id = pessoaEntity.id,
                    nome = pessoaEntity.nome,
                    dataNascimento = pessoaEntity.dataNascimento
                )
            }
        }
    }

    suspend fun inserir(id: Long?, nome: String, dataNascimento: LocalDate) {
        val pessoa = PessoaEntity(
            id = id ?: 0,
            nome = nome,
            dataNascimento = dataNascimento
        )

        pessoaDAO.inserir(pessoa)
    }

    suspend fun remover(id: Long) {
        pessoaDAO.remover(id)
    }
}