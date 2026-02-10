package com.youssef.bosta_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Query("SELECT * FROM games ORDER BY name ASC")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE id = :gameId")
    suspend fun getGameById(gameId: Int): GameEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)
    @Query("SELECT * FROM games WHERE genreSlug = :genreSlug ORDER BY id ASC")
    suspend fun getGamesByGenre(genreSlug: String): List<GameEntity>

    @Query("DELETE FROM games")
    suspend fun clearGames()
}
