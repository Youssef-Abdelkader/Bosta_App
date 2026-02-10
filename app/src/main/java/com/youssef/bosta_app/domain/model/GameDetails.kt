package com.youssef.bosta_app.domain.model


data class GameDetails(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val releasedDate: String,
    val rating: Double,
    val description: String
)
