package cl.duoc.aprendiendoturco.ui.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

/**
 * Manejador del componente nativo de hardware de Android (Vibrador).
 * Proporciona respuesta háptica al recibir respuestas correctas en la app.
 */
class VibradorNativo(context: Context) {

    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        manager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

    /**
     * Emite una vibración nativa al recibir una respuesta correcta (okay).
     */
    fun vibrarExito() {
        vibrator?.let { v ->
            if (v.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val efecto = VibrationEffect.createOneShot(140, VibrationEffect.DEFAULT_AMPLITUDE)
                    v.vibrate(efecto)
                } else {
                    @Suppress("DEPRECATION")
                    v.vibrate(140)
                }
            }
        }
    }
}
