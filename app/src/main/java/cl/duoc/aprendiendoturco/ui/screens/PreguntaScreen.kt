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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.aprendiendoturco.model.Alternativa
import cl.duoc.aprendiendoturco.model.QuizUiState
import cl.duoc.aprendiendoturco.ui.theme.AprendiendoTurcoTheme
import cl.duoc.aprendiendoturco.viewmodel.QuizViewModel

@Composable
fun PreguntaScreen(
    estado: QuizUiState,
    alternativas: List<Alternativa>,
    onResponder: (Int) -> Unit,
    onReintentar: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Soru 1 / 5", style = MaterialTheme.typography.labelLarge)
            Text(
                "Güne başlarken",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text("Ana gözlerini açıyor ve sana gülümsüyor.")
            Text(
                "Ana: «Buenos días, Juan. Ya es hora de levantarse».",
                style = MaterialTheme.typography.titleMedium
            )
            // Traducción directa del diálogo para comprender la frase completa.
            Text(
                "Türkçesi: «Günaydın, Juan. Artık kalkma zamanı».",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                "Ana sana günaydın diyor ve kalkma zamanının geldiğini söylüyor.",
                style = MaterialTheme.typography.bodyMedium
            )
            Text("Ana'ya nasıl cevap verirsin?", style = MaterialTheme.typography.titleMedium)

            // No mostramos cuáles son correctas antes de responder.
            alternativas.forEachIndexed { indice, alternativa ->
                OutlinedButton(
                    onClick = { onResponder(indice) },
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
                    Text("Prototip burada bitiyor. İkinci soru yakında eklenecek.")
                }
                Button(onClick = onReintentar, modifier = Modifier.fillMaxWidth()) {
                    Text(if (respuesta.esCorrecta) "Diğer cevabı dene" else "Tekrar dene")
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreguntaScreenPreview() {
    AprendiendoTurcoTheme {
        PreguntaScreen(QuizUiState(), QuizViewModel().alternativas, {}, {})
    }
}
