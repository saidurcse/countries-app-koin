package com.android.countries.data.repository

import com.android.countries.db.model.CountriesData
import com.android.countries.util.AppResult

interface CountriesRepository {
    suspend fun getAllCountries() : AppResult<List<CountriesData>>
}
