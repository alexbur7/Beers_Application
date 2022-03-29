package com.example.beersapplication.domain.entity

import androidx.annotation.StringRes

data class TypeEntity(
    var isActivated: Boolean = false,
    @StringRes
    val textId: Int
)
