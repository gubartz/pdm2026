package br.edu.ifsp.hto.htoipdm.applazycolumn.data.model

import java.time.LocalDate
import java.time.Period

data class Pessoa(val id: Long, val nome: String, val dataNascimento: LocalDate) {
    fun calcularIdade(): Idade {
        val p = Period.between(dataNascimento, LocalDate.now())

        return Idade(
            anos = p.years,
            meses = p.months,
            dias = p.days
        )
    }
}
