package com.android.countries.di

import com.android.countries.data.api.CountriesApi
import com.android.countries.db.CountriesDao
import com.android.countries.data.repository.CountriesRepository
import com.android.countries.data.repository.CountriesRepositoryImpl
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideCountryRepository(api: CountriesApi, context: Context, dao : CountriesDao): CountriesRepository {
        return CountriesRepositoryImpl(api, context, dao)
    }
    single { provideCountryRepository(get(), androidContext(), get()) }

}