package cl.duoc.aprendiendoturco

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.duoc.aprendiendoturco.model.LoginUiState
import cl.duoc.aprendiendoturco.ui.screens.LoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Prueba de interfaz visual (UI) con Compose UI Test y JUnit,
 * siguiendo los lineamientos de la Guía 15 (Parte 5).
 */
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun elementos_principales_de_login_deben_aparecer_en_pantalla() {
        // Renderizamos la pantalla con estado inicial
        composeRule.setContent {
            LoginScreen(
                estado = LoginUiState(),
                onCorreoChange = {},
                onClaveChange = {},
                onIngresar = {}
            )
        }

        // Validamos que los títulos y botones se muestren correctamente en la UI
        composeRule.onNodeWithText("Türkçeden İspanyolcaya").assertIsDisplayed()
        composeRule.onNodeWithText("Giriş yap / Iniciar sesión").assertIsDisplayed()
        composeRule.onNodeWithText("E-posta / Correo").assertIsDisplayed()
        composeRule.onNodeWithText("Şifre / Contraseña").assertIsDisplayed()
        composeRule.onNodeWithText("Giriş yap / Ingresar").assertIsDisplayed()
    }

    @Test
    fun si_hay_error_el_mensaje_debe_mostrarse_en_pantalla() {
        val mensajeError = "Credenciales incorrectas de prueba"

        // Renderizamos la pantalla con estado que contiene un error
        composeRule.setContent {
            LoginScreen(
                estado = LoginUiState(error = mensajeError),
                onCorreoChange = {},
                onClaveChange = {},
                onIngresar = {}
            )
        }

        // Validamos que el mensaje de error se dibuje en la UI
        composeRule.onNodeWithText(mensajeError).assertIsDisplayed()
    }
}
