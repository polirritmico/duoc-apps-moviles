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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.aprendiendoturco.ui.theme.AprendiendoTurcoTheme

@Composable
fun FinalScreen(onInicio: () -> Unit, onSonidoFinal: () -> Unit = {}) {
    // La marimba suena una vez al entrar al cierre de la historia.
    LaunchedEffect(Unit) {
        onSonidoFinal()
    }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
                .verticalScroll(rememberScrollState()).padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Tebrikler! Hikâyeyi tamamladın.",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Beş soruyu tamamladın. İspanyolca selamlaşmayı, kahvaltı hakkında konuşmayı ve vedalaşmayı çalıştın.",
                style = MaterialTheme.typography.bodyLarge
            )
            Text("Farklı cevapları keşfetmek için baştan oynayabilirsin. Görüşürüz!")
            Text(
                "Bu oyun bir deneme sürümüdür.",
                style = MaterialTheme.typography.bodyMedium
            )
            // Regresa a la bienvenida sin volver a pedir el inicio de sesión.
            Button(onClick = onInicio, modifier = Modifier.fillMaxWidth()) {
                Text("Ana sayfaya dön")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun FinalScreenPreview() {
    AprendiendoTurcoTheme {
        FinalScreen(onInicio = {})
    }
}
