package cl.duoc.aprendiendoturco.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.aprendiendoturco.ui.theme.AprendiendoTurcoTheme

@Composable
fun ContextoScreen(onIniciar: () -> Unit = {}) {
    Scaffold { innerPadding ->
        // El desplazamiento permite leer la historia en pantallas pequeñas.
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Yeni bir ev, yeni bir sabah",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            // Contexto en turco para quienes están aprendiendo español.
            Text(
                text = "Sen Juan'sın. Ana ile evlisin ve üç çocuğunuz var.",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Ailenle kısa süre önce yeni bir eve taşındınız. Masrafları karşılamak için artık çift vardiya çalışıyorsun.",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Sabah oldu. İşe gitmeden önce ailenle kahvaltı yapmak istiyorsun. Ana yanında uyanıyor…",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Görevin: Juan'ın ne söyleyeceğini seç. Seçimlerin hikâyeyi değiştirecek.",
                style = MaterialTheme.typography.titleMedium
            )

            // Abre la primera pregunta de la historia.
            Button(
                onClick = onIniciar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Hikâyeye başla")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun ContextoScreenPreview() {
    AprendiendoTurcoTheme {
        ContextoScreen()
    }
}
