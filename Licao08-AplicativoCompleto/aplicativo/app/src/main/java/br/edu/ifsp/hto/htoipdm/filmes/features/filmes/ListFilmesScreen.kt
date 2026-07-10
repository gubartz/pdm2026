package br.edu.ifsp.hto.htoipdm.filmes.features.filmes

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthViewModel

@Composable
fun ListFilmesScreen(autViewModel: AuthViewModel = hiltViewModel()) {
    Text("Olá filmes")

    Button(
        onClick = {
            autViewModel.logout()
        }
    ) {
        Text("Sair")
    }
}