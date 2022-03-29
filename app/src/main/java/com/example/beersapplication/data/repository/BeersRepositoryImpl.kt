package com.example.beersapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.beersapplication.data.db.source.BeersDbSource
import com.example.beersapplication.data.service.source.BeersServiceSource
import com.example.beersapplication.domain.repository.BeersRepository
import com.example.beersapplication.domain.entity.BeerEntity
import com.example.beersapplication.presentation.util.ErrorHandler
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class BeersRepositoryImpl @Inject constructor(
    private val beersDbSource: BeersDbSource,
    private val beersServiceSource: BeersServiceSource,
    private val errorHandler: ErrorHandler
) : BeersRepository {

    override val errorMessageIdData: MutableLiveData<Int> = MutableLiveData()

    override fun getAllBeers(): Observable<List<BeerEntity>> {
        return Observable.concat(
            beersDbSource.getAllBeers(),
            beersServiceSource.getAllBeers()
                .doOnNext {
                    beersDbSource.insertBeers(it)
                }.onErrorResumeNext {
                    errorMessageIdData.postValue(errorHandler.getErrorStringIdByThrowable(it))
                    beersDbSource.getAllBeers()
                }
        )
    }

    override fun getStrongBeers(fortress: Float): Observable<List<BeerEntity>> {
        return Observable.concat(
            beersDbSource.getStrongBeers(fortress),
            beersServiceSource.getStrongBeers(fortress)
                .doOnNext {
                    beersDbSource.insertBeers(it)
                }
        ).onErrorResumeNext {
            errorMessageIdData.postValue(errorHandler.getErrorStringIdByThrowable(it))
            beersDbSource.getStrongBeers(fortress)
        }
    }
}
