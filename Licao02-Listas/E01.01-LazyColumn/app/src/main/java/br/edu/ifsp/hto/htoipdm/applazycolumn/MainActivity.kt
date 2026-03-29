package br.edu.ifsp.hto.htoipdm.applazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.mock.getNomes
import br.edu.ifsp.hto.htoipdm.applazycolumn.ui.theme.AppLazyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLazyColumnTheme {
                /*
                TODO(5) Colocamos o nosso composable me um Box. O Box é um conteiner que permite
                 posicionar elementos dentro dele. O safeDrawingPadding adiciona automaticamente
                 espaçamento (padding) para evitar que o conteúdo fique por baixo de áreas do
                 sistema, como barra de status (topo), etc.
                 */
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
    TODO(6) Utilizar a função getNomes para recuperar a lista de nomes da função correspondente no
     arquivo ListaNomes.kt. A lista de nomes poderia vir de um banco de dados local ou webservice,
     por exemplo, mas aqui deixamos fixa.
     */
    val nomes = getNomes()

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
        TODO(7) Adicionar o LazyColumn, que é uma lista vertical eficiente e com rolagem que só
         renderiza os itens que estão visíveis na tela.
         */
        LazyColumn {
            /*
            TODO(8) Adicionar a função items(items: List<T>), que é uma função que recebe uma lista
             de itens e renderiza cada um deles. Cuicado, pois existem outras funções items, que
             recebem parâmetros diferentes.
             */
            items(nomes) { nome ->
                Text(text = nome)
                /*
                TODO(9) Adicionar um separador entre os itens da lista.
                 */
                HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppLazyColumnTheme {
        ListaNomes()
    }
}