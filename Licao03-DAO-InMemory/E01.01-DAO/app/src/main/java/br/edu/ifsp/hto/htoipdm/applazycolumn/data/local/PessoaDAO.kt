package br.edu.ifsp.hto.htoipdm.applazycolumn.data.local

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import java.time.LocalDate

interface PessoaDAO {
    fun listar(): List<Pessoa>

    fun inserir(nome: String, dataNascimento: LocalDate)

    fun atualizar(pessoa: Pessoa)

    fun remover(id: Long)
}