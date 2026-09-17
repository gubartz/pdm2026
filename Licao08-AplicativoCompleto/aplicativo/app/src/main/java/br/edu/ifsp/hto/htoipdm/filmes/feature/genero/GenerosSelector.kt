package br.edu.ifsp.hto.htoipdm.filmes.feature.genero

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenerosSelector(
    generos: List<Genero>,
    generosSelecionados: List<Genero>,
    onGeneroChange: (Genero) -> Unit
) {
    var showSheet by remember {
        mutableStateOf(false)
    }

    OutlinedCard(
        onClick = {
            showSheet = true
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Gêneros",
                style = MaterialTheme.typography.labelMedium
            )

            if (generosSelecionados.isEmpty()) {
                Text("Selecionar gêneros")
            } else {
                Text(
                    text = generosSelecionados
                        .joinToString(", ") { it.descricao }
                )
            }
        }
    }

    if (showSheet) {
        GenerosBottomSheet(
            generos = generos,
            selecionados = generosSelecionados,
            onGeneroChange = onGeneroChange,
            onConfirm = {
                showSheet = false
            },
            onDismiss = {
                showSheet = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerosBottomSheet(
    generos: List<Genero>,
    selecionados: List<Genero>,
    onGeneroChange: (Genero) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f) // ocupa até 80% da tela
        ) {
            Text(
                text = "Selecionar gêneros",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(24.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(generos) { genero ->

                    val checked = genero in selecionados

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onGeneroChange(genero)
                            }
                            .padding(
                                horizontal = 24.dp,
                                vertical = 8.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = checked,
                            onCheckedChange = {
                                onGeneroChange(genero)
                            },
                        )

                        Text(genero.descricao)
                    }
                }
            }

            Button(
                onClick = {
                    onConfirm()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text("Confirmar")
            }
        }

    }
}