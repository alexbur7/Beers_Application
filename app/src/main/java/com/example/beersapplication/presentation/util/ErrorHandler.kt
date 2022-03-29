package com.example.beersapplication.presentation.util

import com.example.beersapplication.R
import retrofit2.HttpException
import javax.inject.Inject

class ErrorHandler @Inject constructor() {

    fun getErrorStringIdByThrowable(throwable: Throwable): Int {
        return when (throwable) {
            is HttpException -> {
                R.string.server_error
            }
            else ->
                R.string.connection_error
        }
    }
}
