package br.edu.ifsp.hto.htoipdm.applazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val nomes = remember { mutableStateListOf<String>() }
    var nomeEntrada by remember { mutableStateOf("") }

    var indiceEdicao by remember { mutableIntStateOf(-1) }

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
                    if (nomeEntrada.isNotBlank()) {
                        if (indiceEdicao != -1) {
                            nomes[indiceEdicao] = nomeEntrada
                            indiceEdicao = -1
                        } else {
                            nomes.add(nomeEntrada)
                        }
                        nomes.sortWith(
                            compareBy { it.lowercase() }
                        )
                        nomeEntrada = ""
                    }
                }
            ) {
                Text(text = if (indiceEdicao == -1) "Adicionar" else "Editar")
            }
        }

        LazyColumn {
            itemsIndexed(nomes) { indice, nome ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        /*
                        TODO(1) Adicionar o clickable para que a edição seja feita ao se clicar no
                         Card ao invés de um botão.
                         */
                        .clickable(
                            onClick = {
                                indiceEdicao = indice
                                nomeEntrada = nome
                            }
                        )
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