package com.example.beersapplication.data.service.source

import com.example.beersapplication.data.mapper.BeerApiToBeerEntityMapper
import com.example.beersapplication.data.service.AppService
import com.example.beersapplication.domain.entity.BeerEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface BeersServiceSource {

    fun getAllBeers(): Observable<List<BeerEntity>>

    fun getStrongBeers(fortress: Float): Observable<List<BeerEntity>>
}

class BeersServiceSourceImpl @Inject constructor(
    private val appService: AppService,
    private val beerMapper: BeerApiToBeerEntityMapper
) : BeersServiceSource {

    override fun getAllBeers(): Observable<List<BeerEntity>> {
        return appService.getAllBeers()
            .map { beers ->
                beers.map(beerMapper)
            }
            .toObservable()
    }

    override fun getStrongBeers(fortress: Float): Observable<List<BeerEntity>> {
        return appService.getStrongBeers(fortress)
            .map { beers ->
                beers.sortedBy { it.fortress }.map(beerMapper)
            }
            .toObservable()
    }
}
