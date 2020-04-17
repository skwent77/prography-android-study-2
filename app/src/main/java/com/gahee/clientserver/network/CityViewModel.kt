package com.gahee.clientserver.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

enum class NetworkState{
    LOADING, DONE, ERROR
}

class CityViewModel : ViewModel() {

    private val _citiesLiveData : MutableLiveData<List<City>> = MutableLiveData()
    val citiesLiveData : LiveData<List<City>>
        get() = _citiesLiveData

    private val _networkState : MutableLiveData<NetworkState> = MutableLiveData()
    val networkState : LiveData<NetworkState>
        get() = _networkState

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private suspend fun fetchCitiesDataFromNetwork(){
        try {
            _networkState.value =
                NetworkState.LOADING
            val cityList = CitiesApi.retrofitService.getCities()
            fetchCityPhotos(cityList.cities)
            _networkState.value =
                NetworkState.DONE
//            _citiesLiveData.value = cityList.cities
        }catch (e : Exception){
            e.printStackTrace()
            _networkState.value =
                NetworkState.ERROR
        }
    }

    private suspend fun fetchCityPhotos(cities: List<City>) {
        cities.forEach{
            try {
                val cityPhotoResult: CityPhotoResult =
                    PhotosApi.unsplashApiService.getPhotos(
                        UNSPLASH_ACCESS_KEY, it.cityName, 1
                    )
//                println(cityPhotoResult.photos[0].urls.smallSizedUrl)
                it.cityPhotoUrl = cityPhotoResult.photos[0].urls.regularSizedUrl
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
        _citiesLiveData.value = cities
    }

    //만약 이미 가져온 정보가 있다면 해당 정보를 사용한다.
    fun fetchCitiesData(){
        if(_citiesLiveData.value.isNullOrEmpty()){
            uiScope.launch {
                fetchCitiesDataFromNetwork()
            }
        }else{
            _citiesLiveData.value = citiesLiveData.value
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}