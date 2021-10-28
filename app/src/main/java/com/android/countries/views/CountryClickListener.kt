package com.android.countries.views

import com.android.countries.db.model.CountriesData

interface CountryClickListener {
    fun onItemClick(country : CountriesData)
}