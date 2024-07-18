package com.example.tellcom.service.constants

import android.content.Context
import com.example.telecom.R

class ConstantsOrders private constructor(){
   object ORDERS {
       // Para acessar as strings, precisamos de um contexto
       lateinit var context: Context
       val SAVED_ORDER: String
           get() = context.getString(R.string.save_order)
       val NOT_SAVED_ORDER: String
           get() = context.getString(R.string.save_order_fail)
       val INVALID_PROTOCOL_NUMBER: String
           get() = context.getString(R.string.invalid_protocol_number)
       val PROTOCOL_NUMBER_REQUERIED: String
           get() = context.getString(R.string.protocol_number_requeried)
       val SAVE_ORDER_ERROR_TAG: String
           get() = context.getString(R.string.save_order_error_tag)
       val SAVE_ORDER_ERROR_MESSAGE: String
           get() = context.getString(R.string.save_order_error_message)
       val UPDATE_ORDER_ERROR_TAG: String
           get() = context.getString(R.string.save_score_error_tag)
       val UPDATE_ORDER_ERROR_MESSAGE: String
           get() = context.getString(R.string.save_score_error_message)
       val DELETE_ORDER_ERROR_TAG: String
           get() = context.getString(R.string.delete_order_error_tag)
       val DELETE_ORDER_ERROR_MESSAGE: String
           get() = context.getString(R.string.delete_order_error_message)
   }
}