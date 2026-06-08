package br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.ApiClient
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.remote.PessoaService
import java.time.LocalDate

class PessoaRepository {
    suspend fun listar(): List<Pessoa> {
        val response = ApiClient.pessoaService.getPessoas()
        response.dataResponse?.let {
            return it
        }

        return emptyList()
    }

    suspend fun inserir(id: Long = 0, nome: String, dataNascimento: LocalDate) {
        ApiClient.pessoaService.inserir(
            pessoa = Pessoa(
                id = id,
                nome = nome,
                dataNascimento = dataNascimento
            )
        )
    }

    suspend fun remover(id: Long) {
        ApiClient.pessoaService.remover(id = id)
    }
}