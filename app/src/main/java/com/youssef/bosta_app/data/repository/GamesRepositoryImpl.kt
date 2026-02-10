package com.youssef.bosta_app.data.repository

import com.youssef.bosta_app.data.local.GamesDao
import com.youssef.bosta_app.data.mapper.toDomain
import com.youssef.bosta_app.data.mapper.toEntity
import com.youssef.bosta_app.data.remote.api.GamesApi
import com.youssef.bosta_app.domain.model.Game
import com.youssef.bosta_app.domain.model.GameDetails
import com.youssef.bosta_app.domain.repository.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val api: GamesApi,
    private val dao: GamesDao
) : GamesRepository {

    override suspend fun getGames(
        genreSlug: String,
        page: Int
    ): List<Game> {
        return try {
            val remoteGames = api.getGames(genreSlug = genreSlug, page = page).results
            val domainGames = remoteGames.map { it.toDomain() }
            val entities = domainGames.map { it.toEntity(genreSlug) }
            dao.insertGames(entities)

            domainGames
        } catch (e: Exception) {
            val localGames = dao.getGamesByGenre(genreSlug)
            localGames.map { it.toDomain() }
        }
    }

    override suspend fun getGameDetails(
        gameId: Int
    ): GameDetails {
        return try {
            val remoteDetails = api.getGameDetails(gameId = gameId).toDomain()

            dao.insertGames(listOf(remoteDetails.toEntity()))

            remoteDetails
        } catch (e: Exception) {
            val localGame = dao.getGameById(gameId)
            localGame?.toDomain() ?: throw e
        } as GameDetails
    }
}