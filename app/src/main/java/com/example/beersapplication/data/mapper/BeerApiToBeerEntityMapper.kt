package com.example.beersapplication.data.mapper

import com.example.beersapplication.data.service.api.BeerApi
import com.example.beersapplication.domain.entity.BeerEntity
import javax.inject.Inject

class BeerApiToBeerEntityMapper @Inject constructor() : (BeerApi) -> BeerEntity {
    override fun invoke(beerApi: BeerApi): BeerEntity {
        return BeerEntity(
            id = beerApi.id,
            name = beerApi.name,
            description = beerApi.description,
            url = beerApi.url,
            fortress = beerApi.fortress.toString()
        )
    }
}
