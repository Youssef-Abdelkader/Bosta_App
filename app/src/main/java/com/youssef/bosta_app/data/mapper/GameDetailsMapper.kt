package com.youssef.bosta_app.data.mapper

import com.youssef.bosta_app.data.remote.dto.GameDetailsDto
import com.youssef.bosta_app.domain.model.GameDetails

fun GameDetailsDto.toDomain(): GameDetails {
    return GameDetails(
        id = id,
        name = name,
        imageUrl = backgroundImage.orEmpty(),
        releasedDate = released,
        rating = rating,
        description = description
    )
}


