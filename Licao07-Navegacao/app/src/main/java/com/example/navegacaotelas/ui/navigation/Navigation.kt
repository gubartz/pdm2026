package com.example.navegacaotelas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navegacaotelas.ResultadoSomaScreen
import com.example.navegacaotelas.TelaPrincipalScreen

//TODO(10) Criamos um composable para a navegação.
@Composable
fun AppNavigation() {
    //TODO(11) Declarar uma variávle que terá o histórico da navegação o backStack. O parâmetro
    // TelaPrincipalRoute indica a tela incial de navegação.
    val backStack = rememberNavBackStack(TelaPrincipalRoute)

    //TODO(12) Este composable mantém o backStack, a ação que deve ser executada quando o usuário
    // clica no botão voltar do celular (onBack) e o entryProvider contém o mapeamento das rotas.
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                //TODO(13) Esse is é acionando quando a rota e a TelaPrincipal
                is TelaPrincipalRoute -> NavEntry(key) {
                    //TODO(14) Criamos o composable TelaPrincipalScreen que é a tela principal.
                    TelaPrincipalScreen(
                        //TODO(15) Aqui passamos como parâmetro a lambda que será executada, que
                        // mantém a lógica para acionar a rota do resultado (ResultadoSomaRoute),
                        // que recebe como parâmetro os dois números digitados.
                        onNavigateResultadoSoma = { n1, n2 ->
                            backStack.add(
                                ResultadoSomaRoute(
                                    numero1 = n1,
                                    numero2 = n2
                                )
                            )
                        }
                    )
                }
                //TODO(16) Esse is é para a rota do resultado (ResultadoSomaRoute)
                is ResultadoSomaRoute -> NavEntry(key) {
                    //TODO(17) Chamamos o composable referente à tela recuperando os parâmetros da
                    // key (chave/rota) acionada com os respectivos numero1 e numero2.
                    ResultadoSomaScreen(numero1 = key.numero1, numero2 = key.numero2)
                }


                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}
