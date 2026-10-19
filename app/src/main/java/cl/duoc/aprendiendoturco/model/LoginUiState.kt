package cl.duoc.aprendiendoturco.model

// Datos ingresados en el formulario y mensaje de error.
data class LoginUiState(
    val correo: String = "",
    val clave: String = "",
    val error: String? = null
)
