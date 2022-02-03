package com.itsamirrezah.goscore.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itsamirrezah.goscore.data.models.Team

@Database(entities = arrayOf(Team::class), version = 1, exportSchema = false)
abstract class GoscoreDb : RoomDatabase() {

    abstract fun teamsDao(): TeamsDao

    companion object {

        @Volatile
        private var instance: GoscoreDb? = null

        fun getInstance(context: Context): GoscoreDb {
            if (instance == null)
                instance = Room.databaseBuilder(context, GoscoreDb::class.java, "db")
                    .build()
            return instance!!
        }
    }
}