package cl.duoc.aprendiendoturco.data

import cl.duoc.aprendiendoturco.data.remote.DogApiService
import cl.duoc.aprendiendoturco.data.remote.DogRetrofitInstance
import java.io.IOException
import java.net.URI

class DogRepository(private val api: DogApiService = DogRetrofitInstance.api) {
    suspend fun obtenerImagen(): String {
        val respuesta = api.obtenerPerrito()
        val url = respuesta.message
        val uri = try { url?.let { URI(it) } } catch (_: Exception) { null }
        if (respuesta.status != "success" || uri == null || uri.scheme != "https" || uri.host.isNullOrBlank()) {
            throw IOException("La API no entregó una imagen válida")
        }
        return url ?: throw IOException("La API no entregó una imagen")
    }
}
