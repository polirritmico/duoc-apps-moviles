package cl.duoc.aprendiendoturco.data.remote

import cl.duoc.aprendiendoturco.data.model.DogResponse
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun obtenerPerrito(): DogResponse
}
