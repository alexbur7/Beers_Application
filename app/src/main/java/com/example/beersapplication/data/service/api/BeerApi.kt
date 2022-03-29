package com.example.beersapplication.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeerApi(
    val id: Long,
    val name: String,
    val description: String,
    @SerialName("image_url")
    val url: String,
    @SerialName("abv")
    val fortress: Float
)
