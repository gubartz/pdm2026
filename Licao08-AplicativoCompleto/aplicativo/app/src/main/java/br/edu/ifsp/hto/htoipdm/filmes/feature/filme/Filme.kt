package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.Genero
import com.google.gson.annotations.SerializedName

data class Filme(
    val id: Long,
    val titulo: String,
    @SerializedName("titulo_original")
    val tituloOriginal: String,
    @SerializedName("data_lancamento")
    val dataLancamento: String,
    val duracao: Long,
    val sinopse: String,
    val generos: List<Genero>,
    @SerializedName("poster_path")
    val posterPath: String?
) {
    fun formatDuracao(): String {
        val horas = duracao / 60
        val mins = duracao % 60

        return if (horas > 0) {
            "${horas}h ${mins}min"
        } else {
            "${mins}min"
        }
    }
}