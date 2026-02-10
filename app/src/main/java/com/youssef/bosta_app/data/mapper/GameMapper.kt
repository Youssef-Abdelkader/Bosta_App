package com.youssef.bosta_app.data.mapper

import com.youssef.bosta_app.data.local.GameEntity
import com.youssef.bosta_app.data.remote.dto.GameDto
import com.youssef.bosta_app.domain.model.Game

fun GameDto.toDomain(): Game {
    return Game(
        id = id,
        name = name,
        imageUrl = backgroundImage.orEmpty(),
        rating = rating
    )
}
fun GameEntity.toDomain(): Game {
    return Game(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        rating = this.rating.toDouble()
    )
}