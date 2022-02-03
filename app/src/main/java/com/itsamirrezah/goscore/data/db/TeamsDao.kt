package com.itsamirrezah.goscore.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itsamirrezah.goscore.data.models.Team

@Dao
interface TeamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<Team>)

    @Query("select * from teams where id = :id")
    suspend fun getTeamById(id: Int): Team
}