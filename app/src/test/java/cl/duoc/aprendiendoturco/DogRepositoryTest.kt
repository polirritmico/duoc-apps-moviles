package cl.duoc.aprendiendoturco

import cl.duoc.aprendiendoturco.data.DogRepository
import cl.duoc.aprendiendoturco.data.remote.DogApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class DogRepositoryTest {
    private val server = MockWebServer()
    private lateinit var repository: DogRepository

    @Before
    fun preparar() {
        server.start()
        val api = Retrofit.Builder()
            .baseUrl(server.url("/api/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
        repository = DogRepository(api)
    }

    @After
    fun cerrar() { server.shutdown() }

    @Test
    fun consultaElEndpointYLeeLaDireccionDeLaImagen() = runBlocking {
        server.enqueue(MockResponse().setBody(
            """{"message":"https://images.dog.ceo/perro.jpg","status":"success"}"""
        ))
        assertEquals("https://images.dog.ceo/perro.jpg", repository.obtenerImagen())
        val request = server.takeRequest(2, TimeUnit.SECONDS)
        assertEquals("GET", request?.method)
        assertEquals("/api/breeds/image/random", request?.path)
    }

    @Test
    fun rechazaRespuestasSinImagenValida() {
        for (body in listOf(
            """{"message":"Breed not found","status":"error"}""",
            """{"status":"success"}""",
            """{"message":"http://example.com/perro.jpg","status":"success"}""",
            """{"message":"https:///perro.jpg","status":"success"}"""
        )) {
            server.enqueue(MockResponse().setBody(body))
            assertThrows(IOException::class.java) { runBlocking { repository.obtenerImagen() } }
        }
    }

    @Test
    fun permiteReintentarDespuesDeUnErrorDelServidor() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(503))
        assertThrows(HttpException::class.java) { runBlocking { repository.obtenerImagen() } }
        server.enqueue(MockResponse().setBody(
            """{"message":"https://images.dog.ceo/otro.jpg","status":"success"}"""
        ))
        assertEquals("https://images.dog.ceo/otro.jpg", repository.obtenerImagen())
    }
}
