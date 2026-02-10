package com.youssef.bosta_app.domain.usecase

import com.youssef.bosta_app.domain.model.Game
import com.youssef.bosta_app.domain.repository.GamesRepository

class GetGamesUseCase(
    private val repository: GamesRepository
) {
    suspend operator fun invoke(
        genreSlug: String,
        page: Int
    ): List<Game> {
        return repository.getGames(
            genreSlug = genreSlug,
            page = page
        )
    }
}
