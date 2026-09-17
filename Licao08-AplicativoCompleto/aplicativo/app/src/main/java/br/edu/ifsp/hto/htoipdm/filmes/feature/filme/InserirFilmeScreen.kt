package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.GenerosSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InserirFilmeScreen(
    onVoltar: () -> Unit,
    viewModel: InserirFilmeViewModel = hiltViewModel()
) {
    val generos by viewModel.generos.collectAsStateWithLifecycle()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.filmeSalvo.collect { onVoltar() }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Novo filme") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = uiState.titulo,
                onValueChange = viewModel::onTituloChange,
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.tituloOriginal,
                onValueChange = viewModel::onTituloOriginalChange,
                label = { Text("Título Original") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.dataLancamento,
                onValueChange = viewModel::onDataLancamentoChange,
                label = { Text("Data de lançamento (aaaa-mm-dd)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            OutlinedTextField(
                value = uiState.duracao,
                onValueChange = viewModel::onDuracaoChange,
                label = { Text("Duração (min)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            OutlinedTextField(
                value = uiState.sinopse,
                onValueChange = viewModel::onSinopseChange,
                label = { Text("Sinopse") },
                minLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            GenerosSelector(
                generos = generos,
                generosSelecionados = uiState.generosSelecionados,
                onGeneroChange = viewModel::onGeneroChange
            )

            Button(
                onClick = viewModel::salvar,
                enabled = !uiState.salvando,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                if (uiState.salvando) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp))
                } else {
                    Text("Salvar")
                }
            }
        }
    }

}