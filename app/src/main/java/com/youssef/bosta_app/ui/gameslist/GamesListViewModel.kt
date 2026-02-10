package com.youssef.bosta_app.ui.gameslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youssef.bosta_app.domain.usecase.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesListViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GamesListState())
    val state: StateFlow<GamesListState> = _state

    private var currentPage = 1
    private var isLastPage = false
    val genreSlug = "action"

    init {
        loadGames()
    }

    fun loadGames() {
        if (isLastPage) return

        viewModelScope.launch {
            try {
                if (currentPage == 1) {
                    _state.value = _state.value.copy(isLoading = true, error = null)
                } else {
                    _state.value = _state.value.copy(isPaginationLoading = true, error = null)
                }

                val games = getGamesUseCase(genreSlug, currentPage)
                if (games.isEmpty()) isLastPage = true

                val updatedList = _state.value.games + games
                _state.value = _state.value.copy(
                    isLoading = false,
                    isPaginationLoading = false,
                    games = updatedList
                )

                currentPage++
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    isPaginationLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
    }

    fun retry() {
        loadGames()
    }
}
