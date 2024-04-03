package com.example.tellcom.service.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tellcom.service.model.ScoreModel
@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (score: ScoreModel)



    @Update
    fun update(score: ScoreModel)

    @Query ( "SELECT * FROM score_database")
    fun getAllScore(): LiveData<List<ScoreModel>>


    @Query("DELETE FROM score_database")
    suspend fun deleteAll()

}