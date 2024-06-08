package com.example.tellcom.service.constants

import android.content.Context
import com.example.telecom.R

class Constants private constructor() {

    object DATABASE {
        const val VERSION_ORDER_DATABASE = 30
        const val DATABASE_ORDER_NAME = "orders_database"
        const val DATABASE_SCORE_NAME = "score_database"
    }

    object NOTIFICATION {
        // Para acessar as strings, precisamos de um contexto
        lateinit var context: Context

        // Método para inicializar o contexto
        fun initialize(context: Context) {
            this.context = context
        }

        // Constantes para as mensagens
        val FILLING_IN_FILDS_NOTIFICATION: String
            get() = context.getString(R.string.filling_in_filds)
        val SAVED_ORDER: String
            get() = context.getString(R.string.save_order)
        val SAVED_SCORE: String
            get() = context.getString(R.string.save_score)
        val NOT_SAVED_ORDER: String
            get() = context.getString(R.string.save_order_fail)
        val NOT_SAVED_SCORE: String
            get() = context.getString(R.string.save_score_fail)
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
        val SAVE_SCORE_ERROR_TAG: String
            get() = context.getString(R.string.update_error_order_tag)
        val SAVE_SCORE_ERROR_MESSAGE: String
            get() = context.getString(R.string.update_error_order_message)
        val UPDATE_SCORE_ERROR_TAG: String
            get() = context.getString(R.string.update_error_score_tag)
        val UPDATE_SCORE_ERROR_MESSAGE: String
            get() = context.getString(R.string.update_error_score_message)
        val NUMBER_FORMAT_ERROR: String
            get() = context.getString(R.string.number_format_error)
        val RECALCULATE_SCORE_ERROR_TAG: String
            get() = context.getString(R.string.recalculate_socre_error_tag)
        val RECALCULATE_SCORE_ERROR_MESSAGE: String
            get() = context.getString(R.string.recalculate_socre_error_message)
    }


}