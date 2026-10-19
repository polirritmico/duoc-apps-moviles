package cl.duoc.aprendiendoturco

import cl.duoc.aprendiendoturco.viewmodel.QuizViewModel
import org.junit.Assert.*
import org.junit.Test

class QuizViewModelTest {
    @Test
    fun cadaEleccionCambiaElContextoSiguiente() {
        val cercano = QuizViewModel()
        val formal = QuizViewModel()
        cercano.responder(0)
        formal.responder(3)
        cercano.siguientePregunta()
        formal.siguientePregunta()
        assertEquals(2, cercano.estado.value.numeroPregunta)
        assertNotEquals(cercano.preguntaActual.contexto, formal.preguntaActual.contexto)
        assertNotEquals(cercano.preguntaActual.dialogo, formal.preguntaActual.dialogo)
        assertNull(cercano.estado.value.respuesta)
        // Se puede cambiar de tono: cercano en la primera, formal en la segunda.
        cercano.responder(1)
        formal.responder(2)
        cercano.siguientePregunta()
        formal.siguientePregunta()
        assertEquals("formal", cercano.estado.value.caminoAnterior)
        assertEquals("cercano", formal.estado.value.caminoAnterior)
        assertNotEquals(cercano.preguntaActual.contexto, formal.preguntaActual.contexto)
    }

    @Test
    fun errorNoAvanzaYReintentoConservaLaEscena() {
        val viewModel = QuizViewModel()
        viewModel.siguientePregunta()
        assertEquals(1, viewModel.estado.value.numeroPregunta)
        viewModel.responder(0)
        viewModel.siguientePregunta()
        viewModel.responder(0)
        viewModel.siguientePregunta()
        assertEquals(2, viewModel.estado.value.numeroPregunta)
        viewModel.reintentar()
        assertEquals(2, viewModel.estado.value.numeroPregunta)
        assertEquals("cercano", viewModel.estado.value.caminoAnterior)
    }

    @Test
    fun prototipoTerminaEnLaTerceraYPermiteReiniciar() {
        val viewModel = QuizViewModel()
        for (indice in listOf(0, 1, 3)) {
            viewModel.responder(indice)
            viewModel.siguientePregunta()
        }
        assertEquals(3, viewModel.estado.value.numeroPregunta)
        assertNotNull(viewModel.estado.value.respuesta)
        viewModel.reiniciarHistoria()
        assertEquals(1, viewModel.estado.value.numeroPregunta)
        assertNull(viewModel.estado.value.respuesta)
        assertNull(viewModel.estado.value.caminoAnterior)
    }

    @Test
    fun respuestasCorrectasGuardanCaminosDiferentes() {
        val viewModel = QuizViewModel()
        viewModel.responder(0)
        assertTrue(viewModel.estado.value.respuesta!!.esCorrecta)
        assertEquals("cercano", viewModel.estado.value.respuesta!!.camino)
        viewModel.reintentar()
        viewModel.responder(3)
        assertTrue(viewModel.estado.value.respuesta!!.esCorrecta)
        assertEquals("formal", viewModel.estado.value.respuesta!!.camino)
    }

    @Test
    fun respuestasIncorrectasPermitenReintentarSinElegirCamino() {
        val viewModel = QuizViewModel()
        for (indice in listOf(1, 2)) {
            viewModel.responder(indice)
            assertFalse(viewModel.estado.value.respuesta!!.esCorrecta)
            assertNull(viewModel.estado.value.respuesta!!.camino)
            viewModel.responder(0)
            assertFalse(viewModel.estado.value.respuesta!!.esCorrecta)
            viewModel.reintentar()
            assertNull(viewModel.estado.value.respuesta)
        }
    }
}
