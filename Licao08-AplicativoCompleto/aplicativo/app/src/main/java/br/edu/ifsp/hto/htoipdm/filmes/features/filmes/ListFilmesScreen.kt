package br.edu.ifsp.hto.htoipdm.filmes.features.filmes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthViewModel

@Composable
fun ListFilmesScreen(
    autViewModel: AuthViewModel = hiltViewModel(),
    listFilmesViewModel: ListFilmesViewModel = hiltViewModel()
) {
    val filmes by listFilmesViewModel.filmes.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(filmes) { filme ->
                Text("Título ${filme.titulo}")
            }
        }
        Text("Olá filmes")

        Button(
            onClick = {
                autViewModel.logout()
            }
        ) {
            Text("Sair")
        }
    }

}