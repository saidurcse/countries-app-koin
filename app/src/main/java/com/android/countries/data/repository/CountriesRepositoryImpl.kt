package com.android.countries.data.repository

import com.android.countries.data.api.CountriesApi
import com.android.countries.db.CountriesDao
import com.android.countries.db.model.CountriesData
import com.android.countries.util.AppResult
import com.android.countries.util.NetworkManager.isOnline
import com.android.countries.util.TAG
import com.android.countries.util.Utils.handleApiError
import com.android.countries.util.Utils.handleSuccess
import com.android.countries.util.noNetworkConnectivityError
import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepositoryImpl(
    private val api: CountriesApi,
    private val context: Context,
    private val dao: CountriesDao
) :
    CountriesRepository {

    override suspend fun getAllCountries(): AppResult<List<CountriesData>> {
        if (isOnline(context)) {
            return try {
                val response = api.getAllCountries()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.add(it) }
                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } else {
            //check in db if the data exists
            val data = getCountriesDataFromCache()
            return if (data.isNotEmpty()) {
                Log.d(TAG, "from db")
                AppResult.Success(data)
            } else
            //no network
              context.noNetworkConnectivityError()
        }
    }

    private suspend fun getCountriesDataFromCache(): List<CountriesData> {
        return withContext(Dispatchers.IO) {
            dao.findAll()
        }
    }

/*
This is another way of implementing where the source of data is db and api but we can always fetch from db
which will be updated with the latest data from api and also change findAll() return type to
LiveData<List<CountriesData>>
*/
    /* val data = dao.findAll()

     suspend fun getAllCountriesData() {
         withContext(Dispatchers.IO) {
             val response = api.getAllCountries()
             response.body()?.let {
                 withContext(Dispatchers.IO) { dao.add(it) }
             }
         }
     }*/

}