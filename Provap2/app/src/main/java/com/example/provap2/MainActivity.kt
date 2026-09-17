package com.example.provap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.provap2.data.model.Filme
import com.example.provap2.ui.theme.ProvaP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProvaP2Theme {
                Box(modifier = Modifier.safeContentPadding()) {
                    FilmeList()
                }
            }
        }
    }
}

@Composable
fun FilmeList(viewModel: FilmesViewModel = viewModel()) {
    // TODO(7) Utilizar o collectAsStateWithLifecycle do atributo MutableStateFlow de
    //  FilmesViewModel para observar as alterações.
    val filmes: List<Filme> by viewModel.filmes.collectAsStateWithLifecycle()

    // TODO(8) Mostrar o resultado em uma LazyColumn
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn() {
            items(filmes) { filme ->
                Text(filme.titulo)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun FilmeListPreview() {
    ProvaP2Theme {
        FilmeList()
    }
}