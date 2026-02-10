package com.youssef.bosta_app.data.remote.api

import com.youssef.bosta_app.data.remote.dto.GameDetailsDto
import com.youssef.bosta_app.data.remote.dto.GamesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface GamesApi {

    @GET("games")
    suspend fun getGames(
        @Query("genres") genreSlug: String,
        @Query("page") page: Int
    ): GamesResponseDto

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") gameId: Int
    ): GameDetailsDto
}
