package br.edu.ifsp.hto.htoipdm.filmes

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import br.edu.ifsp.hto.htoipdm.filmes.features.UiEvent
import br.edu.ifsp.hto.htoipdm.filmes.remote.auth.AuthViewModel
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.AppNavigation
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.AuthState
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.authnavigation.SplashScreen
import br.edu.ifsp.hto.htoipdm.filmes.ui.navigation.mainnavigation.MainNavigation
import br.edu.ifsp.hto.htoipdm.filmes.ui.theme.GerenciadorDeFilmesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var uiEventManager: UiEventManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            GerenciadorDeFilmesTheme {
                App(
                    uiEventManager = uiEventManager
                )
            }
        }
    }
}

@Composable
fun App(
    uiEventManager: UiEventManager,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val authState by authViewModel.authState.collectAsState()

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
            when (authState) {
                AuthState.Loading ->
                    SplashScreen()

                AuthState.Unauthenticated ->
                    AppNavigation()

                AuthState.Authenticated ->
                    MainNavigation()
            }
        }
    }
}