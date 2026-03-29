package br.edu.ifsp.hto.somador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.hto.somador.ui.theme.SomadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SomadorTheme {

                Somador()

            }
        }
    }
}

@Composable
fun Somador() {
    val tamanhoFonte = 24.sp

    /*
     TODO(1) Declarar as variáveis abaixo. Elas manterão os valores digitados pelo usuários nos
      campos de entrada de texto.
     */
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }

    /*
     TODO(2) Declarar a variável abaixo.
      A interrogação indica que ela pode ser nula. E inicia com o valor null
     */
    var resultado by remember { mutableStateOf<Double?>(null) }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .padding(top = 32.dp)
        )

        Text(
            text = "Somador",
            fontSize = tamanhoFonte
        )

        /*
        TODO(3) Declarar um OutlinedTextField.
         O OutlinedTextField é um campo de entrada de texto.
         */

        OutlinedTextField(
            /*
            TODO(3) Atribuir a variável numero1 para o parâmetro value. Isso faz com que o valor
             que estiver na variável numero1 fique associada ao campo de entrada de texto.
             */
            value = numero1,
            /*
            TODO(4) O parâmetro onValueChanges recebe uma lambda do tipo (String) -> Unit. Isso
             significa que o valor que estiver no campo de entrada de texto será passado para essa
             lambda. No caso o parâmetro it representa o valor que estiver no campo de entrada de
             texto e atribuímos para a variável numero1.
             */
            onValueChange = {
                numero1 = it
            },
            /*
            TODO(5) Declarar um label para o campo de entrada de texto. O label recebe uma lambda
             com o componente a ser exibido. Nesse caso um componente do tipo Text, mas poderia ser
             um ícone, por exemplo.
             */
            label = {
                Text("Número 1")
            },
        )

        /*
        TODO(6) Repetir o mesmo conceito do campo do numero1 para o numero2
         */
        OutlinedTextField(
            value = numero2,
            onValueChange = {
                numero2 = it
            },
            label = {
                Text("Número 2")
            },
        )

        Button(
            onClick = {
                /*
                TODO(7) O método toDoubleOrNull() retorna um valor Double se a conversão puder ser
                 realizada ou retorna null caso ela falhar.
                 */
                if (numero1.toDoubleOrNull() != null && numero2.toDoubleOrNull() != null) {
                    resultado = numero1.toDouble() + numero2.toDouble()
                } else {
                    resultado = null
                }
            }
        ) {
            Text("Somar")
        }

        /*
        TODO(8) Quando existir um valor diferente de nulo na variável resultado, o texto será
         exibido.
         */
        if (resultado != null) {
            Text("Resultado: $resultado")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SomadorPreview() {
    SomadorTheme {
        Somador()
    }
}