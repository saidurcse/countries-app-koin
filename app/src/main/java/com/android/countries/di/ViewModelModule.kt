package com.android.countries.di

import com.android.countries.views.CountriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build CountriesViewModel
    viewModel {
        CountriesViewModel(repository = get())
    }

}