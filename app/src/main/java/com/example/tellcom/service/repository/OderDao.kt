package com.example.tellcom.service.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tellcom.service.model.OrderModel

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (order : OrderModel)

    @Update
    fun update (order: OrderModel)

    @Query("SELECT * FROM orders_database")
    fun getAllOrders(): LiveData<List<OrderModel>>

    @Query("DELETE FROM orders_database")
    suspend fun deleteAll()


}