package br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository

import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.remote.ApiClient
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.local.PessoaDAO
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.entity.PessoaEntity
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.remote.PessoaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

//TODO(13) Utilizamos a anotação @Inject para injetar as dependências do service e do DAO
class PessoaRepository @Inject constructor(
    private val pessoaService: PessoaService,
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

    suspend fun sincronizar() {
        //TODO(14) Deixar apenas as referências ao pessoaService e não mais ao ApiClient
        val pessoas = pessoaService
                .getPessoas()
                .dataResponse
                ?: return

        pessoaDAO.insertPessoas(
            pessoas.map {
                PessoaEntity(
                    id = it.id,
                    nome = it.nome,
                    dataNascimento = it.dataNascimento
                )
            }
        )
    }

    suspend fun inserir(id: Long = 0, nome: String, dataNascimento: LocalDate) {
        val pessoa = pessoaService.inserir(
            pessoa = Pessoa(
                id = id,
                nome = nome,
                dataNascimento = dataNascimento
            )
        ).dataResponse

        pessoa?.let {
            pessoaDAO.inserir(
                pessoa = PessoaEntity(
                    id = pessoa.id,
                    nome = pessoa.nome,
                    dataNascimento = pessoa.dataNascimento
                )
            )
        }

    }


    suspend fun removerRemoto(id: Long) {
        pessoaService.remover(id = id)
    }

    suspend fun remover(id: Long) {
        pessoaDAO.remover(
            id = id
        )
    }
}