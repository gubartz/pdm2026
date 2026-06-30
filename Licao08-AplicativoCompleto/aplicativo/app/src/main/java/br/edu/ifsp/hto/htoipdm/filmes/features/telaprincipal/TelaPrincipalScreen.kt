package br.edu.ifsp.hto.htoipdm.filmes.features.telaprincipal

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
fun TelaPrincipalScreen() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Somar")
        OutlinedTextField(
            onValueChange = {
                num1 = it
            },
            value = num1,
            label = {
                Text("Número 1")
            }
        )
        OutlinedTextField(
            onValueChange = {
                num2 = it
            },
            value = num2,
            label = {
                Text("Número 2")
            }
        )

        Button(
            onClick = {
                if (num1.toLongOrNull() != null && num2.toLongOrNull() != null) {

                }
            }
        ) {
            Text("Somar")
        }
    }
}

@Preview
@Composable
private fun TelaPrincipalScreenPreview() {
    TelaPrincipalScreen()
}