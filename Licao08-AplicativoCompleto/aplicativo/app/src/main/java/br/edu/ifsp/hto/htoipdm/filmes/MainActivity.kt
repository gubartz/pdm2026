package br.edu.ifsp.hto.htoipdm.filmes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import br.edu.ifsp.hto.htoipdm.filmes.features.UiEvent
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.AppNavigation
import br.edu.ifsp.hto.htoipdm.filmes.ui.theme.GerenciadorDeFilmesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

val LocalUiEventManager =
    staticCompositionLocalOf<UiEventManager> {
        error("UiEventManager not provided")
    }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var uiEventManager: UiEventManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()

        setContent {
            CompositionLocalProvider(
                LocalUiEventManager provides uiEventManager
            ) {
                GerenciadorDeFilmesTheme {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val snackbarHostState = remember { SnackbarHostState() }

    val uiEventManager = LocalUiEventManager.current

    LaunchedEffect(Unit) {

        uiEventManager.events.collect { event ->

            when (event) {

                is UiEvent.Snackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }

                UiEvent.Logout -> {
                    // navigate to login
                }

                else -> {}
            }
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->

        Box(
            Modifier
                .padding(innerPadding)
                .safeDrawingPadding()
        ) {
            AppNavigation()
        }
    }
}