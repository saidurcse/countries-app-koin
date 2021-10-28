package com.android.countries.di

import com.android.countries.db.CountriesDao
import com.android.countries.db.CountriesDatabase
import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): CountriesDatabase {
       return Room.databaseBuilder(application, CountriesDatabase::class.java, "countries")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: CountriesDatabase): CountriesDao {
        return  database.countriesDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }


}
