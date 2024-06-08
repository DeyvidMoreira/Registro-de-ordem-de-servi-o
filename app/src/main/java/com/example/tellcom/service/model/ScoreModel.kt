package com.example.tellcom.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tellcom.service.constants.Constants


@Entity(tableName = Constants.DATABASE.DATABASE_SCORE_NAME)
class ScoreModel (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val jobName: String = "",
    var singlePoints: Double = 0.0,
    var metaScore: Double = 0.0,
    var currentScore: Double = 0.0
)
