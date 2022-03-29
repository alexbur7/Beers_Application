package com.example.beersapplication.di.module

import androidx.lifecycle.ViewModel
import com.example.beersapplication.di.ViewModelKey
import com.example.beersapplication.presentation.menu.MenuViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @[IntoMap ViewModelKey(MenuViewModel::class)]
    fun bindMenuViewModel(menuViewModel: MenuViewModel): ViewModel
}
