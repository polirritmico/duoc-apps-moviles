package cl.duoc.aprendiendoturco.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.aprendiendoturco.model.LoginUiState
import cl.duoc.aprendiendoturco.ui.theme.AprendiendoTurcoTheme

@Composable
fun LoginScreen(
    estado: LoginUiState,
    onCorreoChange: (String) -> Unit,
    onClaveChange: (String) -> Unit,
    onIngresar: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Türkçeden İspanyolcaya", style = MaterialTheme.typography.headlineSmall)
            Text("Giriş yap / Iniciar sesión", style = MaterialTheme.typography.titleLarge)

            // Los campos envían sus cambios al ViewModel.
            OutlinedTextField(
                value = estado.correo,
                onValueChange = onCorreoChange,
                label = { Text("E-posta / Correo") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = estado.error != null,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = estado.clave,
                onValueChange = onClaveChange,
                label = { Text("Şifre / Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = estado.error != null,
                supportingText = {
                    estado.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = onIngresar, modifier = Modifier.fillMaxWidth()) {
                Text("Giriş yap / Ingresar")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun LoginScreenPreview() {
    AprendiendoTurcoTheme {
        LoginScreen(LoginUiState(), {}, {}, {})
    }
}
