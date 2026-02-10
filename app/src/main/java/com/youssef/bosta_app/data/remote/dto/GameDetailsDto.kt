package com.youssef.bosta_app.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameDetailsDto(
    val id: Int,
    val name: String,
    @SerializedName("background_image")
    val backgroundImage: String?,
    val rating: Double,
    val description: String,
    val released: String
)
