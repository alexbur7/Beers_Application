package com.example.beersapplication.domain.entity

data class BeerEntity(
    val id: Long,
    val name: String,
    val description: String,
    val url: String,
    val fortress: String
)
