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
    /*
    TODO(1) Retirar a função getNomes3 e colocamos uma lista vazia.
     */
    val nomes = remember { mutableStateListOf<String>() }
    var nomeEntrada by remember { mutableStateOf("") }
    /*
    TODO(2) Definir uma variável para indicar qual item está em edição. Se o valor estiver em -1
     significa que nenhum item está em edição.
     */
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
                        /*
                        TODO(8) Se um item estiver em edição ele deve ser atualizado. Caso contrário
                         adicionamos o item na lista.
                         */
                        if (indiceEdicao != -1) {
                            nomes[indiceEdicao] = nomeEntrada
                            /*
                            TODO(9) Após a edição temos que indicar que ela terminou atribuindo -1
                             novamente ao indiceEdicao.
                             */
                            indiceEdicao = -1
                        } else {
                            nomes.add(nomeEntrada)
                        }
                        nomes.sortWith(
                            compareBy { it.lowercase() }
                        )
                        /*
                        TODO(10) Atribuir vazio ao nome para limpar o campo.
                         */
                        nomeEntrada = ""
                    }
                }
            ) {
                /*
                TODO(11) Mostrar o texto Editar ou Adicionar a depender da situação.
                 */
                Text(text = if (indiceEdicao == -1) "Adicionar" else "Editar")
            }
        }

        LazyColumn {
            /*
            TODO(3) Trocar para itemsIndexed, pois precisamos saber qual item está sendo editado.
             Por isso guardamos o índice de qual item está sendo editado.
             */
            itemsIndexed(nomes) { indice, nome ->
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
                        /*
                        TODO(4) Colocar os botões dentro de uma Row.
                        */
                        Row(
                            /*
                            TODO(5) Utilizar o spacedBy faz com que todos os elementos dentro da
                             linha tenham um espaçamento igual entre eles.
                             */
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            /*
                            TODO(6) Adicionar um botão para edição com o ícone correspondente.
                             */
                            Button(
                                onClick = {
                                    /*
                                    TODO(7) Ao clicar no botão, guardamos o índice do item que foi
                                     clicado.
                                     */
                                    indiceEdicao = indice
                                    nomeEntrada = nome
                                }
                            ) {
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = "Remover",
                                )
                            }

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
}

@Preview(showBackground = true)
@Composable
fun ListaNomesPreview() {
    AppLazyColumnTheme {
        ListaNomes()
    }
}