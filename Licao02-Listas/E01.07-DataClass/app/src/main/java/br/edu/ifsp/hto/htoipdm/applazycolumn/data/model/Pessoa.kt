package br.edu.ifsp.hto.htoipdm.applazycolumn.data.model

/*
TODO(5) Usar a biblioteca LocalDate
 */
import java.time.LocalDate

/*
TODO(6) Criar uma data class para armazenar o nome e a data de nascimento de uma pessoa.
 */
data class Pessoa(val nome: String, val dataNascimento: LocalDate)
