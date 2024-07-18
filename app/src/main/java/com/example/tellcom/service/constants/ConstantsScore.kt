package com.example.tellcom.service.constants

import android.content.Context
import com.example.telecom.R

class ConstantsScore private constructor(){
    object SCORE {
        // Para acessar as strings, precisamos de um contexto
        lateinit var context: Context
        val SAVED_SCORE: String
            get() = context.getString(R.string.save_score)
        val NOT_SAVED_SCORE: String
            get() = context.getString(R.string.save_score_fail)
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