package br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "pessoa")
data class PessoaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val dataNascimento: LocalDate
)