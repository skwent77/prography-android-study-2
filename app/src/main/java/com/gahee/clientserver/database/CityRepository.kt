package com.gahee.clientserver.database

import androidx.lifecycle.LiveData

class CityRepository(
    private val cityDao: CityDao
) {
    val allCities: LiveData<List<CityEntity>> = cityDao.getAllLikedCities()

    suspend fun insertCityFromRepo(cityEntity: CityEntity) {
        cityDao.insertCity(cityEntity)
    }

    suspend fun deleteCityFromRepo(number: Int) {
        cityDao.deleteCity(number)
    }
}