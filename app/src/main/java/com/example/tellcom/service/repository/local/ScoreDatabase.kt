package com.example.tellcom.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.service.model.ScoreModel
import com.example.tellcom.service.repository.dao.ScoreDao


@Database(entities = [ScoreModel::class], version = Constants.DATABASE.VERSION_SCORE_DATABASE)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: ScoreDatabase? = null

        fun getDatabase(context: Context): ScoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScoreDatabase::class.java,
                    Constants.DATABASE.DATABASE_SCORE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}