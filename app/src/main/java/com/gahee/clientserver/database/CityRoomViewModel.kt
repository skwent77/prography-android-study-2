package com.gahee.clientserver.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityRoomViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CityRepository

    var allCities: LiveData<List<CityEntity>>

    init {
        val cityDao = CityRoomDatabase.getDatabase(application).cityDao()
        repository = CityRepository(cityDao)
        allCities = repository.allCities
    }

    fun insert(cityEntity: CityEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCityFromRepo(cityEntity)
    }

    fun delete(number: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCityFromRepo(number)
    }

}