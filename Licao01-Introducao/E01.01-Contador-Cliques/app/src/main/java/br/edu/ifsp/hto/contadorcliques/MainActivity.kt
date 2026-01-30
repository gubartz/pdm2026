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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
                // TODO(2) Remover o Scaffold.
                ContadorCliques()
            }
        }
    }
}

/*
    TODO(1) Trocar o nome do Composable para ContadorCliques.
    Utilizar shift + F6 para renomear o elemento e todas as referências.
    Remover os parâmetros name e modifier. Utilizar ctrl + F6 para mudar a assinatura.
 */
@Composable
fun ContadorCliques() {
    /*
        TODO(3) Declarar a variável abaixo
    */
    var contador by remember { mutableStateOf(0) }

    /*
        TODO(4) Adicionar uma Column
     */
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
        Text(
            text = "Contador de Cliques",
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Total de Cliques $contador",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                contador++
            },
        ) {
            Text(
                text = "Incrementar",
                fontSize = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorCliquesPreview() {
    ContadorDeCliquesTheme {
        ContadorCliques()
    }
}