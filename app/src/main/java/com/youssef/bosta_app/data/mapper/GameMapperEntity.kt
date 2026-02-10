package com.youssef.bosta_app.data.mapper

import com.youssef.bosta_app.data.local.GameEntity
import com.youssef.bosta_app.domain.model.Game
import com.youssef.bosta_app.domain.model.GameDetails

fun GameDetails.toEntity(genreSlug: String = "unknown") = GameEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    rating = rating.toFloat(),
    releasedDate = releasedDate,
    description = description,
    genreSlug = genreSlug
)
fun Game.toEntity(genreSlug: String = "unknown"): GameEntity {
    return GameEntity(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        rating = String.format("%.2f", this.rating).toFloat(),
        genreSlug = genreSlug,
        releasedDate = "",
        description = ""
    )
}