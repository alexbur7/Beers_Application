package com.example.beersapplication.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beersapplication.domain.repository.BeersRepository
import com.example.beersapplication.domain.entity.BeerEntity
import com.example.beersapplication.presentation.util.ErrorHandler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val beersRepository: BeersRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    val beersData: LiveData<List<BeerEntity>>
        get() = _beersData
    val errorMessageIdData: LiveData<Int>
        get() = beersRepository.errorMessageIdData
    private val _beersData = MutableLiveData<List<BeerEntity>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getAllBeers()
    }

    fun getAllBeers() {
        compositeDisposable.add(beersRepository.getAllBeers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { beers ->
                    _beersData.value = beers
                },
                {
                    beersRepository.errorMessageIdData.value =
                        errorHandler.getErrorStringIdByThrowable(it)
                }
            )
        )
    }

    fun getStrongBeers(fortress: Float = 6f) {
        compositeDisposable.add(
            beersRepository.getStrongBeers(fortress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { beers ->
                        _beersData.value = beers
                    },
                    {
                        beersRepository.errorMessageIdData.value =
                            errorHandler.getErrorStringIdByThrowable(it)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
