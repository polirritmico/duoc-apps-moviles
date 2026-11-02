package cl.duoc.aprendiendoturco.model

sealed interface DogRewardUiState {
    data object Loading : DogRewardUiState
    data class Success(val imageUrl: String) : DogRewardUiState
    data object Error : DogRewardUiState
}
