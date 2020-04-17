package com.gahee.clientserver.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDao{

    @Query("SELECT * FROM city_table ORDER BY number ASC")
    fun getAllLikedCities() : LiveData<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityEntity: CityEntity)

    @Query("DELETE FROM city_table WHERE number = :number")
    suspend fun deleteCity(number : Int)
}