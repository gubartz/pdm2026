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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    /*
    TODO(1) Alterar para a função getNomes3, pois essa função retorna uma mutableStateListOf, que é
     observável. Isso significa que alterações na lista refletem automaticamente na Interface (UI).
     */
    val nomes = remember { getNomes3() }

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

        LazyColumn {
            items(nomes) { nome ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    /*
                    TODO(2) Trocar para Row.
                     */
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        /*
                        TODO(3) Adicionar o verticalAlignment para alinhar os elmentos no centro
                         verticalmente.
                         */
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = nome)
                        /*
                        TODO(4) Adicionar o Spacer. Esse Spacer cresce ocupando todo o espaço
                         disponível até chegar ao próximo componente, que é o botão. Isso faz com
                         que o botão seja "empurrado" para o final da linha.
                         */
                        Spacer(modifier = Modifier.weight(1f))
                        /*
                        TODO(5) Adicionar um botão para remoção do item da lista.
                         */
                        Button(
                            /*
                            TODO(6) Adicionar o código para remoção do item corrente do loop para
                             remoção.
                             */
                            onClick = {
                                nomes.remove(nome)
                            }
                        ) {
                            /*
                            TODO(7) Colocar um ícone para remoção.
                             */
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