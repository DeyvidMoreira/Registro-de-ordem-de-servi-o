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
interface AppDao {

    /*** ORDERS**/

        //Adiciona ordem no db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrders (order : OrderModel)

    //Atualiza lista de ordens salvas
    @Update
    fun updateOrders (order: OrderModel)

    //Busca de ordem por ID
    @Query ("SELECT * FROM orders_database WHERE id = :orderId")
    suspend fun getOrderById(orderId: Int): OrderModel?

    //Pega todas as ordens salvas
    @Query("SELECT * FROM orders_database")
    fun getAllOrders(): LiveData<List<OrderModel>>

    //Busca pelas ordens concluídas
    @Query("SELECT * FROM orders_database WHERE status = 1")
    fun getOrderdesWithStatus1(): LiveData<List<OrderModel>>

    //Deleta a ordem selecionada
    @Query("DELETE FROM orders_database WHERE id = :orderId")
    suspend fun deleteOrderById(orderId: Int): Int

    //Limpa o banco de dados
    @Query("DELETE FROM orders_database")
    suspend fun deleteAllOrders()


    /*** SCORE**/

    //Insere configuração de score no db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScore (score: ScoreModel)

    //Atualiza o score
    @Update
    fun updateScore(score: ScoreModel)

    //Busca todas as configurações de score
    @Query ( "SELECT * FROM score_database")
    fun getAllScore(): LiveData<List<ScoreModel>>

    //Reseta a configuração de score
    @Query("DELETE FROM score_database")
    suspend fun deleteAllScore()
}