package com.android.countries.ui.home

import com.android.countries.db.model.CountriesData
import com.android.countries.data.repository.CountriesRepository
import com.android.countries.util.AppResult
import com.android.countries.util.SingleLiveEvent
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository : CountriesRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val countriesList = MutableLiveData<List<CountriesData>>()
    val showError = SingleLiveEvent<String>()

    fun getAllCountries() {
        showLoading.set(true)
        viewModelScope.launch {
            val result =  repository.getAllCountries()

            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    countriesList.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}