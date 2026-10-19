package cl.duoc.aprendiendoturco.model

// Cada alternativa incluye su explicación y la reacción de Ana.
data class Alternativa(
    val texto: String,
    val esCorrecta: Boolean,
    val titulo: String,
    val reaccion: String,
    val explicacion: String,
    val camino: String? = null,
    val esRuda: Boolean = false
)

data class QuizUiState(
    val respuesta: Alternativa? = null,
    val numeroPregunta: Int = 1,
    val caminoAnterior: String? = null
)

data class Pregunta(
    val titulo: String,
    val contexto: String,
    val dialogo: String,
    val traduccion: String,
    val ayuda: String,
    val alternativas: List<Alternativa>
)
