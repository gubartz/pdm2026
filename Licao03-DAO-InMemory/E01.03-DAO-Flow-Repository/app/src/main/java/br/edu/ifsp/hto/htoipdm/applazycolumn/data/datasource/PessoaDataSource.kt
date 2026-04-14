package br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource

import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import kotlinx.coroutines.flow.MutableStateFlow

object PessoaDataSource {
    val _pessoas = MutableStateFlow<List<Pessoa>>(emptyList())
}