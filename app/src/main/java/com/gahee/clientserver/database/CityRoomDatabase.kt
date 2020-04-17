package com.gahee.clientserver.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
public abstract class CityRoomDatabase : RoomDatabase() {

   abstract fun cityDao(): CityDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CityRoomDatabase? = null

        fun getDatabase(context: Context): CityRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CityRoomDatabase::class.java,
                        "city_database"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
   }

}