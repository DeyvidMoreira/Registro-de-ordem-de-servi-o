package com.example.tellcom.service.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.service.model.ScoreModel

@Dao
interface OrderDao {
    /**
     * ORDERS
    * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrders (order : OrderModel)

    @Update
    fun updateOrders (order: OrderModel)

    @Query("SELECT * FROM orders_database")
    fun getAllOrders(): LiveData<List<OrderModel>>

    @Query("DELETE FROM orders_database")
    suspend fun deleteAllOrders()


    /**
     * SCORE
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScore (score: ScoreModel)



    @Update
    fun updateScore(score: ScoreModel)

    @Query ( "SELECT * FROM score_database")
    fun getAllScore(): LiveData<List<ScoreModel>>


    @Query("DELETE FROM score_database")
    suspend fun deleteAllScore()


}