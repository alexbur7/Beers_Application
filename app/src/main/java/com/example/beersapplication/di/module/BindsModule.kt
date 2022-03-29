package com.example.beersapplication.di.module

import com.example.beersapplication.data.db.source.BeersDbSource
import com.example.beersapplication.data.db.source.BeersDbSourceImpl
import com.example.beersapplication.data.repository.BeersRepositoryImpl
import com.example.beersapplication.data.service.source.BeersServiceSource
import com.example.beersapplication.data.service.source.BeersServiceSourceImpl
import com.example.beersapplication.domain.repository.BeersRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {

    @Binds
    fun bindGetBeerRepository(getBeersRepositoryImpl: BeersRepositoryImpl): BeersRepository

    @Binds
    fun bindBeersDbSource(beersDbSourceImpl: BeersDbSourceImpl): BeersDbSource

    @Binds
    fun bindBeersServiceSource(beersServiceSourceImpl: BeersServiceSourceImpl): BeersServiceSource
}
