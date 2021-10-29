package com.android.countries.ui

import com.android.countries.db.model.CountriesData

interface CountryClickListener {
    fun onItemClick(country : CountriesData)
}