package com.example.beersapplication.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.beersapplication.domain.entity.BeerEntity
import io.reactivex.rxjava3.core.Observable

interface BeersRepository {

    val errorMessageIdData: MutableLiveData<Int>

    fun getAllBeers(): Observable<List<BeerEntity>>

    fun getStrongBeers(fortress: Float): Observable<List<BeerEntity>>
}
