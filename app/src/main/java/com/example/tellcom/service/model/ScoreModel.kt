package com.example.tellcom.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tellcom.service.constants.Constants

@Entity(tableName = Constants.DATABASE.DATABASE_SCORE_NAME)
data class ScoreModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val jobName: String,
    val singlePoints: Double,
    val metaPoints: Double,
    val currentScore: Int = 0
)


