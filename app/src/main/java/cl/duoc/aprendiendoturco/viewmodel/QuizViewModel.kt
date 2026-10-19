package cl.duoc.aprendiendoturco.viewmodel

import androidx.lifecycle.ViewModel
import cl.duoc.aprendiendoturco.model.Alternativa
import cl.duoc.aprendiendoturco.model.QuizUiState
import cl.duoc.aprendiendoturco.model.Pregunta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizViewModel : ViewModel() {
    private val _estado = MutableStateFlow(QuizUiState())
    val estado: StateFlow<QuizUiState> = _estado

    // Datos de la primera pregunta. El camino servirá para la siguiente escena.
    private val alternativasInicio = listOf(
        Alternativa(
            texto = "Buenos días, mi amor. ¿Dormiste bien?",
            esCorrecta = true,
            titulo = "Doğru! Sıcak ve samimi bir cevap.",
            reaccion = "Ana gülümsüyor: «Sí, cariño. ¿Y tú?»",
            explicacion = "Bu cevap sevgi ve yakınlık gösterir. «Buenos días, Ana. Espero que hayas descansado bien» de uygundur, ancak eşler arasında daha resmî duyulur. Bir sonraki oyunda deneyebilirsin.",
            camino = "cercano"
        ),
        Alternativa(
            texto = "No me hinches, quiero seguir durmiendo.",
            esCorrecta = false,
            titulo = "Bu durumda kaba bir cevap.",
            reaccion = "Ana üzülüyor: «Solo quería despertarte…»",
            explicacion = "Şili'de «no me hinches», «beni rahatsız etme» anlamında kullanılır. Bu ifade burada sert duyulur ve Ana'yı kırabilir. Daha nazik bir cevap seç."
        ),
        Alternativa(
            texto = "Buenas noches, mi amor. Que descanses.",
            esCorrecta = false,
            titulo = "Selamlaşmaları karıştırdın.",
            reaccion = "Ana şaşırıyor: «¡Pero si ya es de mañana!»",
            explicacion = "«Buenas noches» akşam veya gece kullanılır. Şimdi sabah olduğu için «Buenos días» demelisin."
        ),
        Alternativa(
            texto = "Buenos días, Ana. Espero que hayas descansado bien.",
            esCorrecta = true,
            titulo = "Doğru! Nazik ve daha resmî bir cevap.",
            reaccion = "Ana gülüyor: «¡Qué serio amaneciste! ¿Dormiste bien?»",
            explicacion = "Bu cevap naziktir, ancak eşler arasında biraz mesafeli duyulabilir. «Buenos días, mi amor. ¿Dormiste bien?» de uygundur ve daha samimidir. Bir sonraki oyunda deneyebilirsin.",
            camino = "formal"
        )
    )

    private val alternativasDesayuno = listOf(
        Alternativa("Estoy chato. Déjame tranquilo.", false,
            "Bu durumda kaba bir cevap.",
            "Ana üzülüyor: «Solo quería saber cómo estás».",
            "Şili'de «estoy chato», «bıktım» anlamına gelir. «Déjame tranquilo» ile birlikte burada kırıcı duyulur."),
        Alternativa("Dormí poco. ¿Te parece si desayunamos antes de que me vaya?", true,
            "Doğru! Nazik ve daha resmî bir cevap.",
            "Ana: «Claro. Prepararé el desayuno mientras te vistes».\nTürkçesi: «Tabii. Sen giyinirken kahvaltıyı hazırlayacağım».",
            "Birlikte kahvaltı yapmayı nazikçe öneriyorsun. «Dormí poquito, amor. ¿Desayunamos juntos?» da uygundur ve daha samimidir. Bir sonraki oyunda deneyebilirsin.", "formal"),
        Alternativa("Dormí poquito, amor. ¿Desayunamos juntos?", true,
            "Doğru! Samimi bir cevap.",
            "Ana: «Sí, amor. Preparemos algo rápido entre los dos».\nTürkçesi: «Evet, aşkım. Birlikte hızlıca bir şeyler hazırlayalım».",
            "«Poquito», «az» demenin samimi bir yoludur. Diğer uygun cevap «Dormí poco. ¿Te parece si desayunamos antes de que me vaya?» daha resmîdir. Bir sonraki oyunda deneyebilirsin.", "cercano"),
        Alternativa("Dormí poco. Vamos a cenar antes de salir esta mañana.", false,
            "Öğünleri karıştırdın.",
            "Ana şaşırıyor: «¿Cenar? ¡Si es de mañana!»",
            "«Cenar», akşam yemeği yemek demektir. Sabah kahvaltı yapmak için «desayunar» kullanılır.")
    )

    private val alternativasCafe = listOf(
        Alternativa("Un cafecito, porfa, amor.", true,
            "Doğru! Samimi ve günlük bir cevap.",
            "Ana: «Aquí tienes, amor. Me sentaré contigo».\nTürkçesi: «Buyur, aşkım. Yanına oturacağım».",
            "«Cafecito» samimi bir ifadedir; «porfa», «por favor» ifadesinin günlük kısaltmasıdır. «Quisiera un café, por favor» da uygundur ve daha resmîdir. Bir sonraki oyunda deneyebilirsin.", "cercano"),
        Alternativa("Pásame un café al tiro, po. ¡Apúrate!", false,
            "Bu durumda kaba bir cevap.",
            "Ana: «Puedes pedírmelo con más calma».\nTürkçesi: «Benden daha sakin bir şekilde isteyebilirsin».",
            "Şili'de «al tiro», «hemen» demektir. «Po», konuşma dilinde vurgu için kullanılır. Bu sözler tek başına kaba değildir; burada emir vermek ve «¡Apúrate!» demek cevabı kaba yapar."),
        Alternativa("Quisiera comer un café, por favor.", false,
            "Fiili karıştırdın.",
            "Ana: «El café se toma, no se come».\nTürkçesi: «Kahve içilir, yenmez».",
            "«Comer», yemek demektir. Kahve için «tomar» veya «beber» kullanılır."),
        Alternativa("Quisiera un café, por favor.", true,
            "Doğru! Nazik ve daha resmî bir cevap.",
            "Ana şaka yapıyor: «Enseguida, señor Juan».\nTürkçesi: «Hemen, Juan Bey».",
            "«Quisiera» nazikçe bir şey istemek için kullanılır. «Un cafecito, porfa, amor» da uygundur ve eşler arasında daha samimidir. Bir sonraki oyunda deneyebilirsin.", "formal")
    )

    // Solo cambia el contexto según la elección de la escena anterior.
    val preguntaActual: Pregunta
        get() {
            val cercano = _estado.value.caminoAnterior == "cercano"
            return when (_estado.value.numeroPregunta) {
                2 -> Pregunta(
                    "Kısa bir kahvaltı molası",
                    if (cercano) "Ana sevgi dolu selamına gülümsedi. Sana biraz yaklaşıyor."
                    else "Ana resmî selamına güldü. Bu sabah neden bu kadar ciddi olduğunu merak ediyor.",
                    if (cercano) "Ana: «Sí, cariño. ¿Y tú? ¿Dormiste bien?»"
                    else "Ana: «¡Qué serio amaneciste! ¿Dormiste bien?»",
                    if (cercano) "Evet, canım. Ya sen? İyi uyudun mu?"
                    else "Bu sabah ne kadar ciddisin! İyi uyudun mu?",
                    "Bugün iki vardiya çalışacaksın ve biraz yorgunsun. Ana nasıl uyuduğunu soruyor.",
                    alternativasDesayuno
                )
                3 -> Pregunta(
                    "Mutfakta bir kahve",
                    if (cercano) "Ana ile mutfağa gidiyorsunuz. Kahvaltıyı birlikte hazırlıyorsunuz."
                    else "Sen giyinirken Ana fincanları hazırladı. Şimdi mutfağa geliyorsun.",
                    if (cercano) "Ana: «Preparemos el desayuno juntos. ¿Qué quieres tomar?»"
                    else "Ana: «Ya preparé las tazas. ¿Qué quieres tomar?»",
                    if (cercano) "Kahvaltıyı birlikte hazırlayalım. Ne içmek istersin?"
                    else "Fincanları hazırladım. Ne içmek istersin?",
                    "Ana kahvaltıda ne içmek istediğini soruyor.",
                    alternativasCafe
                )
                else -> Pregunta(
                    "Güne başlarken", "Ana gözlerini açıyor ve sana gülümsüyor.",
                    "Ana: «Buenos días, Juan. Ya es hora de levantarse».",
                    "Günaydın, Juan. Artık kalkma zamanı",
                    "Ana sana günaydın diyor ve kalkma zamanının geldiğini söylüyor.",
                    alternativasInicio
                )
            }
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
        if (!respuesta.esCorrecta || actual.numeroPregunta >= 3) return
        _estado.value = QuizUiState(
            numeroPregunta = actual.numeroPregunta + 1,
            caminoAnterior = respuesta.camino
        )
    }

    fun reiniciarHistoria() {
        _estado.value = QuizUiState()
    }
}
