package com.youssef.bosta_app.domain.repository

import com.youssef.bosta_app.domain.model.Game
import com.youssef.bosta_app.domain.model.GameDetails

interface GamesRepository {

    suspend fun getGames(
        genreSlug: String,
        page: Int
    ): List<Game>

    suspend fun getGameDetails(
        gameId: Int
    ): GameDetails
}
