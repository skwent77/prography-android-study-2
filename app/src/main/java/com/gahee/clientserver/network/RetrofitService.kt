package com.gahee.clientserver.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://progserver.herokuapp.com/api/cites
private const val BASE_URL = "https://progserver.herokuapp.com/api/"

//https://api.unsplash.com/photos/?client_id=YOUR_ACCESS_KEY
private const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"

const val UNSPLASH_ACCESS_KEY = "HLYPFMwSl765d7YC_uAYwTSoTZEM_XkfvjhqwTXz-6A"

private val retrofitCities
        = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

private val retrofitUnsplash
        = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(UNSPLASH_BASE_URL)
    .build()


interface CitiesApiService{

    @GET("cities")
    suspend fun getCities() : CityList

}

interface UnsplashApiService{
    @GET("/search/photos")
    suspend fun getPhotos(@Query("client_id") accessKey : String,
        @Query("query") query : String,
        @Query("per_page") perPage : Int) : CityPhotoResult
}

object CitiesApi{
    val retrofitService : CitiesApiService by lazy {
        retrofitCities.create(CitiesApiService::class.java)
    }
}

object PhotosApi{
    val unsplashApiService : UnsplashApiService by lazy{
        retrofitUnsplash.create(UnsplashApiService::class.java)
    }
}