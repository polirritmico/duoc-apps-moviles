package cl.duoc.aprendiendoturco

import cl.duoc.aprendiendoturco.data.DogRepository
import cl.duoc.aprendiendoturco.data.model.DogResponse
import cl.duoc.aprendiendoturco.data.remote.DogApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import java.io.IOException

/**
 * Prueba unitaria para el repositorio usando MockK y Corrutinas Test,
 * siguiendo los lineamientos de la Guía 15 (Parte 4).
 */
class DogRepositoryMockKTest {

    @Test
    fun obtenerImagenRetornaUrlCuandoApiRespondeExitosamente() = runTest {
        // 1. Simulación de la respuesta esperada
        val fakeResponse = DogResponse(
            message = "https://images.dog.ceo/breeds/retriever-golden/perro.jpg",
            status = "success"
        )

        // 2. Mock de DogApiService con MockK
        val mockApi = mockk<DogApiService>()
        coEvery { mockApi.obtenerPerrito() } returns fakeResponse

        // 3. Inyección de dependencia simulada al repositorio
        val repository = DogRepository(api = mockApi)

        // 4. Ejecución y aserción
        val url = repository.obtenerImagen()
        assertEquals("https://images.dog.ceo/breeds/retriever-golden/perro.jpg", url)

        // Verificamos que se llamó al método de la API exactamente 1 vez
        coVerify(exactly = 1) { mockApi.obtenerPerrito() }
    }

    @Test
    fun obtenerImagenLanzaExcepcionCuandoStatusEsError() = runTest {
        val mockApi = mockk<DogApiService>()
        coEvery { mockApi.obtenerPerrito() } returns DogResponse(
            message = "Breed not found",
            status = "error"
        )

        val repository = DogRepository(api = mockApi)

        assertThrows(IOException::class.java) {
            kotlinx.coroutines.runBlocking {
                repository.obtenerImagen()
            }
        }
    }

    @Test
    fun obtenerImagenRechazaUrlsQueNoSonHttps() = runTest {
        val mockApi = mockk<DogApiService>()
        coEvery { mockApi.obtenerPerrito() } returns DogResponse(
            message = "http://inseguro.com/perro.jpg",
            status = "success"
        )

        val repository = DogRepository(api = mockApi)

        assertThrows(IOException::class.java) {
            kotlinx.coroutines.runBlocking {
                repository.obtenerImagen()
            }
        }
    }
}
