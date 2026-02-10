package com.youssef.bosta_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val rating: Float,
    val releasedDate: String?,
    val description: String?,
    val genreSlug: String
)
