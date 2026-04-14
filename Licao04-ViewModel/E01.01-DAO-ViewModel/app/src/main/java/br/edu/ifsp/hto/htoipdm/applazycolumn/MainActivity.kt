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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.local.PessoaDAOImpl
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.model.Pessoa
import br.edu.ifsp.hto.htoipdm.applazycolumn.data.repository.PessoaRepository
import br.edu.ifsp.hto.htoipdm.applazycolumn.ui.theme.AppLazyColumnTheme
import br.edu.ifsp.hto.htoipdm.ui_components.DataPickerField
import java.time.LocalDate
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
    val pessoaViewModel = viewModel<PessoaViewModel> {
        PessoaViewModel(
            pessoaRepository = PessoaRepository(
                PessoaDAOImpl()
            )
        )
    }

    val pessoas by pessoaViewModel.pessoas.collectAsStateWithLifecycle()

    val dateFormatter =
        DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.getDefault())
    var nome by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var pessoaEdicao by remember { mutableStateOf<Pessoa?>(null) }

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
                value = nome,
                onValueChange = {
                    nome = it
                },
                label = {
                    Text(text = "Nome")
                }
            )

            DataPickerField(
                label = "Data de Nascimento",
                value = selectedDate,
                onValueChange = {
                    selectedDate = it
                },
                maxDate = LocalDate.now()
            )
        }

        Button(
            onClick = {
                if (nome.isBlank() || selectedDate == null) return@Button

                selectedDate?.let { date ->
                    pessoaEdicao?.let {
                        pessoaViewModel.atualizar(
                            it.copy(
                                nome = nome,
                                dataNascimento = date
                            )
                        )

                    } ?: pessoaViewModel.inserir(nome, date)
                    nome = ""
                    selectedDate = null
                    pessoaEdicao = null
                }
            }
        ) {
            Text(text = if (pessoaEdicao == null) "Adicionar" else "Editar")
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
                                nome = pessoa.nome
                                selectedDate = pessoa.dataNascimento
                                pessoaEdicao = pessoa
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