package com.youssef.bosta_app.ui.gameslist

import com.youssef.bosta_app.domain.model.Game

data class GamesListState(
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
) {
    val filteredGames: List<Game>
        get() = if (searchQuery.isBlank()) games
        else games.filter { it.name.contains(searchQuery, ignoreCase = true) }
}
