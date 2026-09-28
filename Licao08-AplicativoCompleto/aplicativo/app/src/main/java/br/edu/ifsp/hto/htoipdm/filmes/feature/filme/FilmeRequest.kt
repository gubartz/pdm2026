package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.Genero
import com.google.gson.annotations.SerializedName

data class FilmeRequest(
    val titulo: String,
    @SerializedName("titulo_original")
    val tituloOriginal: String,
    @SerializedName("data_lancamento")
    val dataLancamento: String,
    val duracao: Long,
    val sinopse: String,
    val generos: List<Genero>
)
