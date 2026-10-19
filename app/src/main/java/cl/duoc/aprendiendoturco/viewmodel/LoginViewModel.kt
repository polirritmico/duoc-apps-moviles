package cl.duoc.aprendiendoturco.viewmodel

import androidx.lifecycle.ViewModel
import cl.duoc.aprendiendoturco.model.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _estado = MutableStateFlow(LoginUiState())
    val estado: StateFlow<LoginUiState> = _estado

    // Cuentas de prueba para el ejercicio. No se conecta a un servidor.
    private val usuarios = mapOf(
        "ro.callealta@duocuc.cl" to "hola1234",
        "ed.bray@duocuc.cl" to "hola1234"
    )

    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, error = null) }
    }

    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, error = null) }
    }

    fun validarLogin(): Boolean {
        val actual = _estado.value
        if (actual.correo.isBlank() || actual.clave.isBlank()) {
            _estado.update { it.copy(error = "Correo y contraseña obligatorios / E-posta ve şifre gerekli") }
            return false
        }
        val correo = actual.correo.trim().lowercase()
        if (usuarios[correo] != actual.clave) {
            _estado.update { it.copy(error = "Correo o contraseña incorrectos / E-posta veya şifre hatalı") }
            return false
        }
        // Limpiamos el formulario después de ingresar.
        _estado.value = LoginUiState()
        return true
    }
}
