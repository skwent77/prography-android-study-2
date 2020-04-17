package com.gahee.clientserver.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class City(
    @SerializedName("no")
    val number : Int,

    val url : String,

    @SerializedName("city")
    val cityName : String
): Parcelable{

    var cityPhotoUrl : String? = null
        get(){
            return field
        }
        set(value) {
            field = value

        }
}


@Parcelize
data class CityList(
    val cities : List<City>
) : Parcelable



//결과 받는 클래스
@Parcelize
data class CityPhotoResult(
    @SerializedName("results")
    val photos : List<CityPhoto>
) : Parcelable


//보조 클래스
@Parcelize
data class CityPhoto(
    @SerializedName("urls")
    val urls : PhotoUrl
):Parcelable

@Parcelize
data class PhotoUrl(
    @SerializedName("regular")
    val regularSizedUrl : String
): Parcelable
