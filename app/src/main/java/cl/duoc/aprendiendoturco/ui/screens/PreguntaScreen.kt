package cl.duoc.aprendiendoturco.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.aprendiendoturco.model.Pregunta
import cl.duoc.aprendiendoturco.model.QuizUiState
import cl.duoc.aprendiendoturco.ui.theme.AprendiendoTurcoTheme
import cl.duoc.aprendiendoturco.viewmodel.QuizViewModel
import cl.duoc.aprendiendoturco.ui.utils.SonidosQuiz

@Composable
fun PreguntaScreen(
    estado: QuizUiState,
    pregunta: Pregunta,
    onResponder: (Int) -> Unit,
    onReintentar: () -> Unit,
    onSiguiente: () -> Unit,
    onReiniciar: () -> Unit
) {
    val contexto = LocalContext.current
    val sonidos = remember(contexto) { SonidosQuiz(contexto) }
    // Cargamos una vez y liberamos el audio al salir de la pantalla.
    DisposableEffect(sonidos) {
        onDispose { sonidos.liberar() }
    }
    // Cada nueva pregunta comienza con el desplazamiento arriba.
    key(estado.numeroPregunta) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Soru ${estado.numeroPregunta} / 5", style = MaterialTheme.typography.labelLarge)
            Text(
                pregunta.titulo,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(pregunta.contexto)
            Text(
                pregunta.dialogo,
                style = MaterialTheme.typography.titleMedium
            )
            // Traducción directa del diálogo para comprender la frase completa.
            Text(
                "Türkçesi: «${pregunta.traduccion}».",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                pregunta.ayuda,
                style = MaterialTheme.typography.bodyMedium
            )
            Text("Ana'ya nasıl cevap verirsin?", style = MaterialTheme.typography.titleMedium)

            // No mostramos cuáles son correctas antes de responder.
            pregunta.alternativas.forEachIndexed { indice, alternativa ->
                OutlinedButton(
                    onClick = {
                        if (estado.numeroPregunta == 5 && alternativa.esCorrecta) {
                            sonidos.reproducirFinal()
                        } else {
                            sonidos.reproducirSeleccion()
                        }
                        onResponder(indice)
                    },
                    enabled = estado.respuesta == null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("${'A' + indice}) ${alternativa.texto}")
                }
            }

            // La explicación aparece debajo de las alternativas para cualquier respuesta.
            estado.respuesta?.let { respuesta ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Seçimin: ${respuesta.texto}", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            respuesta.titulo,
                            style = MaterialTheme.typography.titleMedium,
                            color = if (respuesta.esCorrecta) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.error
                        )
                        Text(respuesta.reaccion)
                        Text(respuesta.explicacion)
                    }
                }
                if (respuesta.esCorrecta) {
                    if (estado.numeroPregunta < 5) {
                        Button(onClick = onSiguiente, modifier = Modifier.fillMaxWidth()) {
                            Text("Devam et")
                        }
                    } else {
                        Text("Tebrikler! Beş soruluk hikâyeyi tamamladın.", style = MaterialTheme.typography.titleMedium)
                        Text(
                            if (respuesta.camino == "cercano")
                                "Ana ve çocuklar sana sevgiyle veda ediyor. Yorgunsun, ama ailenin desteğiyle işe gidiyorsun."
                            else
                                "Ana sana iyi bir iş günü diliyor. Çocukların gününün nasıl geçtiğini akşam anlatacak. Sen de işe doğru yola çıkıyorsun."
                        )
                        Text("Farklı tepkileri görmek için yeniden oyna ve diğer uygun cevapları dene.")
                        Button(onClick = onReiniciar, modifier = Modifier.fillMaxWidth()) {
                            Text("Hikâyeyi yeniden başlat")
                        }
                    }
                }
                Button(onClick = onReintentar, modifier = Modifier.fillMaxWidth()) {
                    Text(if (respuesta.esCorrecta) "Diğer cevabı dene" else "Tekrar dene")
                }
            }
        }
    }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreguntaScreenPreview() {
    AprendiendoTurcoTheme {
        PreguntaScreen(QuizUiState(), QuizViewModel().preguntaActual, {}, {}, {}, {})
    }
}
