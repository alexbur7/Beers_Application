package com.example.beersapplication.data.service

import com.example.beersapplication.data.service.api.BeerApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET("beers")
    fun getAllBeers(): Single<List<BeerApi>>

    @GET("beers")
    fun getStrongBeers(@Query("abv_gt") fortress: Float): Single<List<BeerApi>>
}
