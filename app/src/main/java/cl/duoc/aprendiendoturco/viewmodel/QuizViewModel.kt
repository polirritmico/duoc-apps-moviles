package cl.duoc.aprendiendoturco.viewmodel

import androidx.lifecycle.ViewModel
import cl.duoc.aprendiendoturco.model.QuizChoice
import cl.duoc.aprendiendoturco.model.QuizUiState
import cl.duoc.aprendiendoturco.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizViewModel : ViewModel() {
    private val _estado = MutableStateFlow(QuizUiState())
    val estado: StateFlow<QuizUiState> = _estado

    // Datos de la primera pregunta. El camino servirá para la siguiente escena.
    private val alternativasInicio = listOf(
        QuizChoice(
            texto = "Buenos días, mi amor. ¿Dormiste bien?",
            esCorrecta = true,
            titulo = "Doğru! Sıcak ve samimi bir cevap.",
            reaccion = "Ana gülümsüyor: «Sí, cariño. ¿Y tú?»",
            explicacion = "Bu cevap sevgi ve yakınlık gösterir. «Buenos días, Ana. Espero que hayas descansado bien» de uygundur, ancak eşler arasında daha resmî duyulur. Bir sonraki oyunda deneyebilirsin.",
            camino = "cercano"
        ),
        QuizChoice(
            texto = "No me hinches, quiero seguir durmiendo.",
            esCorrecta = false,
            titulo = "Bu durumda kaba bir cevap.",
            reaccion = "Ana üzülüyor: «Solo quería despertarte…»",
            explicacion = "Şili'de «no me hinches», «beni rahatsız etme» anlamında kullanılır. Bu ifade burada sert duyulur ve Ana'yı kırabilir. Daha nazik bir cevap seç.",
            esRuda = true
        ),
        QuizChoice(
            texto = "Buenas noches, mi amor. Que descanses.",
            esCorrecta = false,
            titulo = "Selamlaşmaları karıştırdın.",
            reaccion = "Ana şaşırıyor: «¡Pero si ya es de mañana!»",
            explicacion = "«Buenas noches» akşam veya gece kullanılır. Şimdi sabah olduğu için «Buenos días» demelisin."
        ),
        QuizChoice(
            texto = "Buenos días, Ana. Espero que hayas descansado bien.",
            esCorrecta = true,
            titulo = "Doğru! Nazik ve daha resmî bir cevap.",
            reaccion = "Ana gülüyor: «¡Qué serio amaneciste! ¿Dormiste bien?»",
            explicacion = "Bu cevap naziktir, ancak eşler arasında biraz mesafeli duyulabilir. «Buenos días, mi amor. ¿Dormiste bien?» de uygundur ve daha samimidir. Bir sonraki oyunda deneyebilirsin.",
            camino = "formal"
        )
    )

    private val alternativasDesayuno = listOf(
        QuizChoice("Estoy chato. Déjame tranquilo.", false,
            "Bu durumda kaba bir cevap.",
            "Ana üzülüyor: «Solo quería saber cómo estás».",
            "Şili'de «estoy chato», «bıktım» anlamına gelir. «Déjame tranquilo» ile birlikte burada kırıcı duyulur.", esRuda = true),
        QuizChoice("Dormí poco. ¿Te parece si desayunamos antes de que me vaya?", true,
            "Doğru! Nazik ve daha resmî bir cevap.",
            "Ana: «Claro. Prepararé el desayuno mientras te vistes».\nTürkçesi: «Tabii. Sen giyinirken kahvaltıyı hazırlayacağım».",
            "Birlikte kahvaltı yapmayı nazikçe öneriyorsun. «Dormí poquito, amor. ¿Desayunamos juntos?» da uygundur ve daha samimidir. Bir sonraki oyunda deneyebilirsin.", "formal"),
        QuizChoice("Dormí poquito, amor. ¿Desayunamos juntos?", true,
            "Doğru! Samimi bir cevap.",
            "Ana: «Sí, amor. Preparemos algo rápido entre los dos».\nTürkçesi: «Evet, aşkım. Birlikte hızlıca bir şeyler hazırlayalım».",
            "«Poquito», «az» demenin samimi bir yoludur. Diğer uygun cevap «Dormí poco. ¿Te parece si desayunamos antes de que me vaya?» daha resmîdir. Bir sonraki oyunda deneyebilirsin.", "cercano"),
        QuizChoice("Dormí poco. Vamos a cenar antes de salir esta mañana.", false,
            "Öğünleri karıştırdın.",
            "Ana şaşırıyor: «¿Cenar? ¡Si es de mañana!»",
            "«Cenar», akşam yemeği yemek demektir. Sabah kahvaltı yapmak için «desayunar» kullanılır.")
    )

    private val alternativasCafe = listOf(
        QuizChoice("Un cafecito, porfa, amor.", true,
            "Doğru! Samimi ve günlük bir cevap.",
            "Ana: «Aquí tienes, amor. Me sentaré contigo».\nTürkçesi: «Buyur, aşkım. Yanına oturacağım».",
            "«Cafecito» samimi bir ifadedir; «porfa», «por favor» ifadesinin günlük kısaltmasıdır. «Quisiera un café, por favor» da uygundur ve daha resmîdir. Bir sonraki oyunda deneyebilirsin.", "cercano"),
        QuizChoice("Pásame un café al tiro, po. ¡Apúrate!", false,
            "Bu durumda kaba bir cevap.",
            "Ana: «Puedes pedírmelo con más calma».\nTürkçesi: «Benden daha sakin bir şekilde isteyebilirsin».",
            "Şili'de «al tiro», «hemen» demektir. «Po», konuşma dilinde vurgu için kullanılır. Bu sözler tek başına kaba değildir; burada emir vermek ve «¡Apúrate!» demek cevabı kaba yapar.", esRuda = true),
        QuizChoice("Quisiera comer un café, por favor.", false,
            "Fiili karıştırdın.",
            "Ana: «El café se toma, no se come».\nTürkçesi: «Kahve içilir, yenmez».",
            "«Comer», yemek demektir. Kahve için «tomar» veya «beber» kullanılır."),
        QuizChoice("Quisiera un café, por favor.", true,
            "Doğru! Nazik ve daha resmî bir cevap.",
            "Ana şaka yapıyor: «Enseguida, señor Juan».\nTürkçesi: «Hemen, Juan Bey».",
            "«Quisiera» nazikçe bir şey istemek için kullanılır. «Un cafecito, porfa, amor» da uygundur ve eşler arasında daha samimidir. Bir sonraki oyunda deneyebilirsin.", "formal")
    )

    private val alternativasHijos = listOf(
        QuizChoice("Qué lata, hazlo tú.", false,
            "Bu durumda kaba bir cevap.",
            "Ana: «Yo también necesito ayuda».\nTürkçesi: «Benim de yardıma ihtiyacım var».",
            "Şili'de «qué lata», bir şeyin sıkıcı veya can sıkıcı olduğunu anlatır. Burada «hazlo tú» ile birlikte yardım etmek istemediğini sertçe söylüyorsun.", esRuda = true),
        QuizChoice("Sí, amor. Los despierto y vuelvo al tiro.", true,
            "Doğru! Samimi ve yardımsever bir cevap.",
            "Ana: «Gracias, amor. Los esperamos para desayunar».\nTürkçesi: «Teşekkürler, aşkım. Kahvaltı için onları bekliyoruz».",
            "«Al tiro», Şili'de «hemen» demektir. Burada kaba değildir: yardım etmeyi kabul ediyorsun. «Por supuesto. Iré a despertarlos antes de salir» da uygundur ve daha resmîdir.", "cercano"),
        QuizChoice("Por supuesto. Iré a despertarlos antes de salir.", true,
            "Doğru! Nazik ve daha resmî bir cevap.",
            "Ana: «Muchas gracias. Voy a terminar de preparar sus cosas».\nTürkçesi: «Çok teşekkürler. Eşyalarını hazırlamayı bitireceğim».",
            "«Por supuesto», «elbette» demektir. «Sí, amor. Los despierto y vuelvo al tiro» da uygundur ve daha samimidir. Bir sonraki oyunda deneyebilirsin.", "formal"),
        QuizChoice("Sí, voy a acostarlos para que empiecen el día.", false,
            "Eylemleri karıştırdın.",
            "Ana: «¡Necesitan levantarse, no acostarse!»\nTürkçesi: «Kalkmaları gerekiyor, yatmaları değil!»",
            "«Acostar», birini yatırmak demektir. Güne başlamak için çocukları uyandırmalısın: «despertar».")
    )

    private val alternativasDespedida = listOf(
        QuizChoice("Hasta luego, Ana. Que tengan un buen día.", true,
            "Doğru! Nazik ve daha resmî bir veda.",
            "Ana: «Que te vaya bien, Juan. Después te cuento cómo nos fue».\nTürkçesi: «İşlerin yolunda gitsin, Juan. Sonra günümüzün nasıl geçtiğini anlatırım».",
            "«Que tengan un buen día» birden fazla kişiye iyi günler dilemek için kullanılır. «Chao, mi amor. Nos vemos en la noche. ¡Los quiero!» da uygundur ve daha samimidir.", "formal"),
        QuizChoice("Bienvenida, Ana. Nos vemos ayer.", false,
            "Vedalaşmayı ve zamanı karıştırdın.",
            "Ana: «¿Ayer? Querrás decir después».\nTürkçesi: «Dün mü? Sonra demek istiyorsun herhalde».",
            "«Bienvenida», gelen birini karşılamak için kullanılır. «Ayer» geçmişteki «dün» demektir. Vedalaşırken «hasta luego» veya «chao» diyebilirsin."),
        QuizChoice("Ya, me voy. No me den jugo.", false,
            "Bu durumda kaba bir veda.",
            "Ana: «Podrías despedirte con más cariño».\nTürkçesi: «Daha sevgi dolu bir şekilde vedalaşabilirsin».",
            "Şili'de «no me den jugo», bağlama göre «beni rahatsız etmeyin» veya «işimi zorlaştırmayın» anlamına gelebilir. Burada ailene karşı kırıcı duyulur.", esRuda = true),
        QuizChoice("Chao, mi amor. Nos vemos en la noche. ¡Los quiero!", true,
            "Doğru! Sevgi dolu ve samimi bir veda.",
            "Ana ve çocuklar: «¡Te queremos! ¡Que te vaya bien!»\nTürkçesi: «Seni seviyoruz! İşlerin yolunda gitsin!»",
            "«Chao» günlük bir vedadır. «Los quiero» ailene sevgini gösterir. «Hasta luego, Ana. Que tengan un buen día» da uygundur ve daha resmîdir. Bir sonraki oyunda deneyebilirsin.", "cercano")
    )

    // Solo cambia el contexto según la elección de la escena anterior.
    val preguntaActual: QuizQuestion
        get() {
            val cercano = _estado.value.caminoAnterior == "cercano"
            return when (_estado.value.numeroPregunta) {
                2 -> QuizQuestion(
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
                3 -> QuizQuestion(
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
                4 -> QuizQuestion(
                    "Çocukları uyandırma zamanı",
                    if (cercano) "Ana kahveni verip yanına oturdu. Çocuklardan biri odasından sesleniyor."
                    else "Ana «Juan Bey» diye şaka yaparak kahveni verdi. Çocuklardan biri odasından sesleniyor.",
                    if (cercano) "Ana: «Amor, ¿puedes despertar a los otros dos?»"
                    else "Ana: «Juan, ¿podrías despertar a los otros dos, por favor?»",
                    if (cercano) "Aşkım, diğer ikisini uyandırabilir misin?"
                    else "Juan, diğer ikisini uyandırabilir misin, lütfen?",
                    "Çocuklardan biri uyandı. Ana diğer iki çocuğu uyandırmanı istiyor.",
                    alternativasHijos
                )
                5 -> QuizQuestion(
                    "İşe gitmeden önce",
                    if (cercano) "Çocukları uyandırdın. Şimdi herkes mutfakta kahvaltı ediyor. İşe gitme zamanın geldi."
                    else "Çocukları uyandırıp çantalarını hazırladın. Ana sana teşekkür ediyor ve kapıya kadar eşlik ediyor.",
                    if (cercano) "Ana: «Ya es hora de salir, amor. ¡Que te vaya bien!»"
                    else "Ana: «Gracias por ayudar, Juan. Que tengas un buen día».",
                    if (cercano) "Artık çıkma zamanı, aşkım. İşlerin yolunda gitsin!"
                    else "Yardımın için teşekkürler, Juan. İyi günler.",
                    "Bugün iki vardiya çalışacaksın. Evden çıkmadan önce ailene veda et.",
                    alternativasDespedida
                )
                else -> QuizQuestion(
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
