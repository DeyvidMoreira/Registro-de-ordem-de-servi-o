package com.example.tellcom.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tellcom.service.constants.Constants

enum class OrderStatus {
    //VERDE
    DONE,
    //VERDE
    NOT_DONE,
    //BRANCO
    IN_PROGRESS
}

@Entity(tableName = Constants.DATABASE.DATABASE_NAME)
data class OrderModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = Constants.ENTETY.PRIMARY_ID,
    val protocolNumber: String,
    val clientName: String,
    val dropValue: String,
    var isDone: Boolean = true
)