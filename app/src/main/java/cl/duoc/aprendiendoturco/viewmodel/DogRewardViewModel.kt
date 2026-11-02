package cl.duoc.aprendiendoturco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.aprendiendoturco.data.DogRepository
import cl.duoc.aprendiendoturco.model.DogRewardUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogRewardViewModel(private val repository: DogRepository = DogRepository()) : ViewModel() {
    private val _estado = MutableStateFlow<DogRewardUiState>(DogRewardUiState.Loading)
    val estado = _estado.asStateFlow()
    private var solicitud: Job? = null

    init { cargarRecompensa() }

    // El ViewModel conserva la fotografía al girar el teléfono o recomponer la pantalla.
    fun cargarRecompensa() {
        if (solicitud?.isActive == true) return
        solicitud = viewModelScope.launch {
            _estado.value = DogRewardUiState.Loading
            try {
                _estado.value = DogRewardUiState.Success(repository.obtenerImagen())
            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {
                _estado.value = DogRewardUiState.Error
            }
        }
    }
}
