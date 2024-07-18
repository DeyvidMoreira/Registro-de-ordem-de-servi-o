package com.example.tellcom.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tellcom.service.constants.ConstantsDatabase
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.service.model.ScoreModel
import com.example.tellcom.service.repository.dao.AppDao

@Database(
    entities = [OrderModel::class, ScoreModel::class],
    version = ConstantsDatabase.DATABASE.VERSION_ORDER_DATABASE
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    ConstantsDatabase.DATABASE.DATABASE_ORDER_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}