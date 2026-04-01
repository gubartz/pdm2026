package br.edu.ifsp.hto.htoipdm.applazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.mock.getPessoas
import br.edu.ifsp.hto.htoipdm.applazycolumn.ui.theme.AppLazyColumnTheme
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLazyColumnTheme {
                Box(
                    modifier = Modifier.safeDrawingPadding()
                ) {
                    ListaAniversarios()
                }
            }
        }
    }
}

@Composable
fun ListaAniversarios() {
    /*
    TODO(8) Chamar a função getPessoas para retornar uma lista de objetos de Pessoa
     */
    val pessoas = remember { getPessoas() }

    /*
    TODO(9) Criar um formatter para formatar a data conforme o formato configurado no celular.
     */
    val dateFormatter =
        DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.getDefault())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*
        TODO(10) Trocar o text do Text abaixo.
         */
        Text(
            text = "Aniversários",
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
        )

        LazyColumn {
            /*
            TODO(11) Trocar o parâmetro do itemsIndexed para pessoas, na lambda usamos indice e
             pessoa. Com isso, a cada iteração na lista pessoas o parâmetro nomeado pessoa recebe
             um objeto do tipo Pessoa
             */
            itemsIndexed(pessoas) { indice, pessoa ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                    ) {
                        /*
                        TODO(12) Acessar os atributos referentes ao nome e a data de nascimento.
                         */
                        Text(
                            text = pessoa.nome,
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        /*
                        TODO(13) Utilizar o dateFormatter para mostrar a data no formato configurado
                         no celular.
                         */
                        Text(text = pessoa.dataNascimento.format(dateFormatter))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaAniversariosPreview() {
    AppLazyColumnTheme {
        ListaAniversarios()
    }
}