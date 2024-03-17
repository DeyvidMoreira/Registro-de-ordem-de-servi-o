package com.example.tellcom.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tellcom.service.constants.Constants



@Entity(tableName = Constants.DATABASE.DATABASE_ORDER_NAME)
data class OrderModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val protocolNumber: String,
    val clientName: String,
    val dropValue: String,
    var isDone: Boolean = true
)