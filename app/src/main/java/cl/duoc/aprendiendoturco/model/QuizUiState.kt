package cl.duoc.aprendiendoturco.model

data class QuizUiState(
    val respuesta: QuizChoice? = null,
    val numeroPregunta: Int = 1,
    val caminoAnterior: String? = null
)