package br.edu.ifsp.hto.htoipdm.applazycolumn.data.local

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface PessoaDAO {
    fun listar(): Flow<List<Pessoa>>

    suspend fun inserir(nome: String, dataNascimento: LocalDate)

    suspend fun atualizar(pessoa: Pessoa)

    suspend fun remover(id: Long)
}