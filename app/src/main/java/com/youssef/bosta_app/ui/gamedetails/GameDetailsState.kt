package com.youssef.bosta_app.ui.gamedetails

import com.youssef.bosta_app.domain.model.GameDetails

data class GameDetailsState(
    val isLoading: Boolean = false,
    val game: GameDetails? = null,
    val error: String? = null
)
