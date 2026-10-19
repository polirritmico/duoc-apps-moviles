package cl.duoc.aprendiendoturco.ui.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import cl.duoc.aprendiendoturco.R
import cl.duoc.aprendiendoturco.model.Alternativa
import java.util.concurrent.ConcurrentHashMap

// Cuatro reacciones y un sonido adicional al completar la historia.
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

    // El listener agrega cada sonido cuando termina de cargar.
    private val sonidosListos = ConcurrentHashMap.newKeySet<Int>()
    private val sonidoCercano: Int
    private val sonidoFormal: Int
    private val sonidoRudo: Int
    private val sonidoConfusion: Int
    private val sonidoFinal: Int

    init {
        soundPool.setOnLoadCompleteListener { _, id, resultado ->
            if (resultado == 0) {
                sonidosListos.add(id)
            }
        }
        sonidoCercano = soundPool.load(contexto, R.raw.respuesta_cercana, 1)
        sonidoFormal = soundPool.load(contexto, R.raw.respuesta_formal, 1)
        sonidoRudo = soundPool.load(contexto, R.raw.respuesta_ruda, 1)
        sonidoConfusion = soundPool.load(contexto, R.raw.respuesta_confusion, 1)
        sonidoFinal = soundPool.load(contexto, R.raw.final_historia, 1)
    }

    fun reproducirRespuesta(alternativa: Alternativa) {
        val sonido = when {
            alternativa.esCorrecta && alternativa.camino == "cercano" -> sonidoCercano
            alternativa.esCorrecta -> sonidoFormal
            alternativa.esRuda -> sonidoRudo
            else -> sonidoConfusion
        }
        reproducir(sonido)
    }

    fun reproducirFinal() {
        reproducir(sonidoFinal)
    }

    private fun reproducir(sonido: Int) {
        if (sonidosListos.contains(sonido)) {
            // Volumen izquierdo, derecho, prioridad, repeticiones y velocidad.
            soundPool.play(sonido, 0.6f, 0.6f, 1, 0, 1f)
        }
    }

    fun liberar() {
        soundPool.release()
    }
}
