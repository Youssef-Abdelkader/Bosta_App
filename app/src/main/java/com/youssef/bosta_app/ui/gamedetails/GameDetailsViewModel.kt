package com.youssef.bosta_app.ui.gamedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youssef.bosta_app.domain.usecase.GetGameDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GameDetailsState())
    val state: StateFlow<GameDetailsState> = _state

    fun loadGameDetails(gameId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val details = getGameDetailsUseCase(gameId)
                _state.value = _state.value.copy(
                    isLoading = false,
                    game = details
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun retry(gameId: Int) {
        loadGameDetails(gameId)
    }
}
