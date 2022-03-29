package com.example.beersapplication.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "Beers")
data class BeerDb(
    @PrimaryKey
    val id: Long = 1L,
    val name: String,
    val description: String,
    @SerialName("image_url")
    val url: String,
    val fortress: Float
)
