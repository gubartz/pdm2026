package br.edu.ifsp.hto.htoipdm.filmes.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun LoginScreen(
    onLogin: () -> Unit
) {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Tela de Login")

        OutlinedTextField(
            onValueChange = {
                usuario = it
            },
            value = usuario,
            label = {
                Text("Usuário")
            }
        )
        OutlinedTextField(
            onValueChange = {
                senha = it
            },
            value = senha,
            label = {
                Text("Senha")
            }
        )

        Button(
            onClick = {
                onLogin()
            }
        ) {
            Text("Entrar")
        }
    }
}

@Preview
@Composable
private fun LoginScreenScreenPreview() {
    LoginScreen(
        onLogin = {}
    )
}