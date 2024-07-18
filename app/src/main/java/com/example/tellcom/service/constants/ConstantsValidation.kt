package com.example.tellcom.service.constants

import android.content.Context
import com.example.telecom.R

class ConstantsValidation {

    object VALIDATION {
        // Para acessar as strings, precisamos de um contexto
        lateinit var context: Context
        val FILLING_IN_FILDS_NOTIFICATION: String
            get() = context.getString(R.string.filling_in_filds)
    }

}