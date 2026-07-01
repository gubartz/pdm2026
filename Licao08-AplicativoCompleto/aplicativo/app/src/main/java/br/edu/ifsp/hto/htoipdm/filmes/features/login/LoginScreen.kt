package br.edu.ifsp.hto.htoipdm.filmes.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fitOutside
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by loginViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            OutlinedTextField(
                value = uiState.usuario,
                onValueChange = loginViewModel::onLoginChange,
                label = { Text("Usuário") }
            )

            OutlinedTextField(
                value = uiState.senha,
                onValueChange = loginViewModel::onSenhaChange,
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = loginViewModel::doLogin,
                enabled = !uiState.loading
            ) {

                if (uiState.loading) {
                    CircularProgressIndicator()
                } else {
                    Text("Login")
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenScreenPreview() {
    LoginScreen()
}