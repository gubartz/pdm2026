package com.example.navegacaotelas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.keepScreenOn

//TODO(7) Criar a tela que faz a soma dos dois números. Note que o composable recebe dois parâmetros
// , referentes aos números 1 e 2 digitados na tela anterior.
@Composable
fun ResultadoSomaScreen(numero1: Long, numero2: Long) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("O resultado de $numero1 + $numero2 é: ${numero1 + numero2}")
    }
}