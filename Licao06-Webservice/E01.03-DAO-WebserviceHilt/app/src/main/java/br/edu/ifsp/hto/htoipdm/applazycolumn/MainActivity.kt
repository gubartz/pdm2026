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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.edu.ifsp.hto.htoipdm.applazycolumn.ui.theme.AppLazyColumnTheme
import br.edu.ifsp.hto.htoipdm.ui_components.DatePickerField
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@AndroidEntryPoint
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
fun ListaAniversarios(pessoaViewModel: PessoaViewModel = hiltViewModel()) {
    val pessoas by pessoaViewModel.pessoas.collectAsStateWithLifecycle()

    val dateFormatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.SHORT)
        .withLocale(LocalLocale.current.platformLocale)


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Aniversários",
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = pessoaViewModel.nome,
                onValueChange = {
                    pessoaViewModel.onNomeChange(
                        valor = it
                    )
                },
                label = {
                    Text(text = "Nome")
                }
            )

            DatePickerField(
                label = "Data de Nascimento",
                value = pessoaViewModel.dataNascimento,
                onValueChange = pessoaViewModel::onDataNascimentoChange,
                maxDate = LocalDate.now()
            )
        }

        Button(
            onClick = {
                pessoaViewModel.salvar()
            }
        ) {
            Text(text = if (pessoaViewModel.modoEdicao) "Editar" else "Salvar")
        }

        HorizontalDivider(modifier = Modifier.padding(8.dp))

        LazyColumn {
            itemsIndexed(pessoas) { indice, pessoa ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable(
                            onClick = {
                                pessoaViewModel.onNomeChange(pessoa.nome)
                                pessoaViewModel.onDataNascimentoChange(pessoa.dataNascimento)
                                pessoaViewModel.idPessoaEdicao = pessoa.id
                            }
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Column {
                            Text(
                                text = pessoa.nome,
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(text = pessoa.dataNascimento.format(dateFormatter))

                            Text(
                                text = "Idade: ${pessoa.calcularIdade().anos} anos " +
                                        "${pessoa.calcularIdade().meses} meses " +
                                        "${pessoa.calcularIdade().dias} dias"
                            )
                        }
                        Button(
                            onClick = {
                                pessoaViewModel.remover(id = pessoa.id)
                            }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
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