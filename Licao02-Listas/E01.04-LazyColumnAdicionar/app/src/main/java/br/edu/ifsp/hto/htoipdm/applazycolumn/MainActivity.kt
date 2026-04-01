package br.edu.ifsp.hto.htoipdm.applazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.mock.getNomes3
import br.edu.ifsp.hto.htoipdm.applazycolumn.ui.theme.AppLazyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLazyColumnTheme {
                Box(
                    modifier = Modifier.safeDrawingPadding()
                ) {
                    ListaNomes()
                }
            }
        }
    }
}

@Composable
fun ListaNomes() {
    val nomes = remember { getNomes3() }
    /*
    TODO(1) Adicionar uma variável para o nome que será inserido na lista.
     */
    var nomeEntrada by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Lista de nomes",
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
        )
        /*
        TODO(2) Adicionar uma linha para conter o campo de entrada de texto de um novo nome e o
         botão para inserção.
         */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = nomeEntrada,
                onValueChange = {
                    nomeEntrada = it
                }
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    /*
                    TODO(3) O isNotBlank verifica se o campo não está vazio e contém ao menos um
                     caractere diferente de vazio.
                     */
                    if (nomeEntrada.isNotBlank()) {
                        nomes.add(nomeEntrada)
                        /*
                        TODO(4) O sortWith permite ordenar os valores. O compareBy retorna um
                         Comparator com o código que está dentro da lambda. No caso, transforma
                         todos os valores em minúsculo para comparação.
                         */
                        nomes.sortWith(
                            compareBy { it.lowercase() }
                        )
                        nomeEntrada = ""
                    }
                }
            ) {
                Text(text = "Adicionar")
            }
        }

        LazyColumn {
            items(nomes) { nome ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = nome)
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = {
                                nomes.remove(nome)
                            }
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Remover",
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaNomesPreview() {
    AppLazyColumnTheme {
        ListaNomes()
    }
}