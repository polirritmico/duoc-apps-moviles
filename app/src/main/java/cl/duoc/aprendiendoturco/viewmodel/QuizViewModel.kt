package cl.duoc.aprendiendoturco.viewmodel

import androidx.lifecycle.ViewModel
import cl.duoc.aprendiendoturco.data.QuizRepository
import cl.duoc.aprendiendoturco.model.QuizUiState
import cl.duoc.aprendiendoturco.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizViewModel(
    private val repository: QuizRepository = QuizRepository()
) : ViewModel() {
    private val _estado = MutableStateFlow(QuizUiState())
    val estado: StateFlow<QuizUiState> = _estado

    val preguntaActual: QuizQuestion
        get() {
            return repository.obtenerPregunta(
                numeroPregunta = _estado.value.numeroPregunta,
                caminoAnterior = _estado.value.caminoAnterior
            )
        }

    fun responder(indice: Int) {
        // Esperamos el reintento antes de permitir otra elección.
        if (_estado.value.respuesta != null) return
        val alternativa = preguntaActual.alternativas.getOrNull(indice) ?: return
        _estado.value = _estado.value.copy(respuesta = alternativa)
    }

    fun reintentar() {
        _estado.value = _estado.value.copy(respuesta = null)
    }

    fun siguientePregunta() {
        val actual = _estado.value
        val respuesta = actual.respuesta ?: return
        if (!respuesta.esCorrecta || actual.numeroPregunta >= 5) return
        _estado.value = QuizUiState(
            numeroPregunta = actual.numeroPregunta + 1,
            caminoAnterior = respuesta.camino
        )
    }

    fun reiniciarHistoria() {
        _estado.value = QuizUiState()
    }
}
