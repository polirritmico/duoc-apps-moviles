package cl.duoc.aprendiendoturco.model

// Cada alternativa incluye su explicación y la reacción de Ana.
data class QuizChoice(
    val texto: String,
    val esCorrecta: Boolean,
    val titulo: String,
    val reaccion: String,
    val explicacion: String,
    val camino: String? = null,
    val esRuda: Boolean = false
)