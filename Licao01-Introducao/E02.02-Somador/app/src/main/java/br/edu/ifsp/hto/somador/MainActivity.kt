package br.edu.ifsp.hto.somador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.somador.ui.theme.SomadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SomadorTheme {

                Somador()

            }
        }
    }
}

@Composable
fun Somador() {
    val tamanhoFonte = 24.sp
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<Double?>(null) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .padding(top = 32.dp)
        )

        Text(
            text = "Somador",
            fontSize = tamanhoFonte
        )

        OutlinedTextField(
            value = numero1,
            onValueChange = {
                numero1 = it
            },
            label = {
                Text("Número 1")
            },
        )

        OutlinedTextField(
            value = numero2,
            onValueChange = {
                numero2 = it
            },
            label = {
                Text("Número 2")
            },
        )

        /*
        TODO(1) Ao invés de termos um botão, podemos realizar a conta tão logo existam dois valores
         válidos nos campos de texto. Isso é possível, pois as variáveis são observáveis.
         */
        if (numero1.toDoubleOrNull() != null && numero2.toDoubleOrNull() != null) {
            resultado = numero1.toDouble() + numero2.toDouble()
            Text("A soma de $numero1 + $numero2 é: $resultado")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SomadorPreview() {
    SomadorTheme {
        Somador()
    }
}