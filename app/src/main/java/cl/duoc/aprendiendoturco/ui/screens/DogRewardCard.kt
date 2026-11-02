package cl.duoc.aprendiendoturco.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cl.duoc.aprendiendoturco.model.DogRewardUiState
import coil3.compose.SubcomposeAsyncImage

@Composable
fun DogRewardCard(estado: DogRewardUiState, onReintentar: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Ödülün: sevimli bir arkadaş!", style = MaterialTheme.typography.titleMedium)
            Text("Perro — Köpek", style = MaterialTheme.typography.titleLarge)
            when (estado) {
                DogRewardUiState.Loading -> {
                    CircularProgressIndicator()
                    Text("Fotoğraf yükleniyor…")
                }
                DogRewardUiState.Error -> DogRewardError(onReintentar)
                is DogRewardUiState.Success -> SubcomposeAsyncImage(
                    model = estado.imageUrl,
                    contentDescription = "Bir köpek fotoğrafı. İspanyolcası: perro.",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth().height(240.dp),
                    loading = {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(Modifier.size(40.dp))
                        }
                    },
                    error = { DogRewardError(onReintentar) }
                )
            }
            Text("Fotoğraf: Dog CEO", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun DogRewardError(onReintentar: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Fotoğraf yüklenemedi. İnternet bağlantını kontrol edip tekrar deneyebilirsin. Oyunu tamamladın!")
        OutlinedButton(onClick = onReintentar) { Text("Tekrar dene") }
    }
}
