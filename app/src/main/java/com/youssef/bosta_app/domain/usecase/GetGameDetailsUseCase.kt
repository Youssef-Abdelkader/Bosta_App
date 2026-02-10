package com.youssef.bosta_app.domain.usecase

import com.youssef.bosta_app.domain.model.GameDetails
import com.youssef.bosta_app.domain.repository.GamesRepository

class GetGameDetailsUseCase(
    private val repository: GamesRepository
) {
    suspend operator fun invoke(
        gameId: Int
    ): GameDetails {
        return repository.getGameDetails(gameId)
    }
}
