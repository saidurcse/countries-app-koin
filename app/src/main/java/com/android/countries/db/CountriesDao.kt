package com.android.countries.db

import com.android.countries.db.model.CountriesData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountriesDao {

    @Query("SELECT * FROM Countries")
    fun findAll(): List<CountriesData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<CountriesData>)
}