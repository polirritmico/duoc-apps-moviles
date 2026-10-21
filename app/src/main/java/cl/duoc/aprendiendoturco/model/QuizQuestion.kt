package cl.duoc.aprendiendoturco.model

data class QuizQuestion(
    val titulo: String,
    val contexto: String,
    val dialogo: String,
    val traduccion: String,
    val ayuda: String,
    val alternativas: List<QuizChoice>
)