package com.gahee.clientserver.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "city_table")
class CityEntity(
    @PrimaryKey
    @ColumnInfo(name = "number")
    val number: Int,

    @ColumnInfo(name = "cityName")
    var cityName: String,

    @ColumnInfo(name = "cityUrl")
    val cityUrl: String,

    @ColumnInfo(name = "cityPhotoUrl")
    val cityPhotoUrl: String?

){
    override fun toString(): String {
        return "CityEntity(number=$number, cityName='$cityName', cityUrl='$cityUrl', cityPhotoUrl=$cityPhotoUrl)"
    }
}

