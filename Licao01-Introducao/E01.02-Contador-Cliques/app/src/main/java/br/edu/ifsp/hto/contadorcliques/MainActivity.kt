package br.edu.ifsp.hto.contadorcliques

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.contadorcliques.ui.theme.ContadorDeCliquesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContadorDeCliquesTheme {
                ContadorCliques()
            }
        }
    }
}

@Composable
fun ContadorCliques() {
    var contador by remember { mutableStateOf(0) }
    val tamanhoFonte = 24.sp

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Spacer(
            modifier = Modifier
                .padding(top = 32.dp)
        )
        Text(
            text = "Contador de Cliques",
            fontSize = tamanhoFonte
        )
        /*
            TODO(1) Utilizar um peso (weight) de 1f. Elementos de mesmo peso ocupam o mesmo espaço.
         */
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Total de Cliques $contador",
            fontSize = tamanhoFonte
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                contador++
            },
        ) {
            Text(
                text = "Incrementar",
                fontSize = tamanhoFonte
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorCliquesPreview() {
    ContadorDeCliquesTheme {
        ContadorCliques()
    }
}