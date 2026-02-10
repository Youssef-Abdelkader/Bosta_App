package com.youssef.bosta_app.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GamesResponseDto(
    @SerializedName("results")
    val results: List<GameDto>
)
