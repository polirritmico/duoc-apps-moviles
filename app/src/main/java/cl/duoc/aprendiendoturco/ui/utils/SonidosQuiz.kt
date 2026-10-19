package cl.duoc.aprendiendoturco.ui.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import cl.duoc.aprendiendoturco.R

// Dos sonidos cortos: seleccionar una alternativa y terminar la historia.
class SonidosQuiz(contexto: Context) {
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(2)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .build()

    @Volatile private var seleccionLista = false
    @Volatile private var finalListo = false
    private var sonidoSeleccion = 0
    private var sonidoFinal = 0

    init {
        soundPool.setOnLoadCompleteListener { _, id, resultado ->
            if (resultado == 0) {
                if (id == sonidoSeleccion) seleccionLista = true
                if (id == sonidoFinal) finalListo = true
            }
        }
        sonidoSeleccion = soundPool.load(contexto, R.raw.seleccion, 1)
        sonidoFinal = soundPool.load(contexto, R.raw.final_historia, 1)
    }

    fun reproducirSeleccion() {
        if (seleccionLista) {
            // Volumen izquierdo, derecho, prioridad, repeticiones y velocidad.
            soundPool.play(sonidoSeleccion, 0.6f, 0.6f, 1, 0, 1f)
        }
    }

    fun reproducirFinal() {
        if (finalListo) {
            soundPool.play(sonidoFinal, 0.6f, 0.6f, 1, 0, 1f)
        }
    }

    fun liberar() {
        soundPool.release()
    }
}
