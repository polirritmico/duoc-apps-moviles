package cl.duoc.aprendiendoturco.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.aprendiendoturco.ui.components.AppBackground
import cl.duoc.aprendiendoturco.ui.components.LanguageBadge
import cl.duoc.aprendiendoturco.ui.theme.AprendiendoTurcoTheme
import cl.duoc.aprendiendoturco.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompact(onComenzar: () -> Unit = {}) {
    AppBackground {
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        // Barra superior de la pantalla principal.
        topBar = {
            TopAppBar(title = { Text("Türkçeden İspanyolcaya") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent))
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LanguageBadge()
            // Bienvenida para personas que hablan turco y quieren aprender español.
            Text(
                "Hoş geldiniz! / ¡Bienvenido!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Türkçeden İspanyolca öğrenmeye başlayın.\nComienza a aprender español desde el turco.",
                style = MaterialTheme.typography.bodyLarge
            )

            // Imagen de las banderas de Turquía y España.
            Image(
                painter = painterResource(id = R.drawable.idiomas),
                contentDescription = "Türkiye ve İspanya bayrakları / Banderas de Turquía y España",
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.large)
                    .padding(20.dp).height(150.dp),
                contentScale = ContentScale.Fit
            )

            // Abre la pantalla que presenta la historia.
            Button(
                onClick = onComenzar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Başla / Comenzar")
            }
        }
    }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun HomeScreenCompactPreview(){
    AprendiendoTurcoTheme {
    HomeScreenCompact()
    }
}
