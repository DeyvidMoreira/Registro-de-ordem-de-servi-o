package com.example.tellcom.service.constants

import android.content.Context
import com.example.telecom.R

class ConstantsDialog private constructor() {

    object DIALOG {
        // Para acessar as strings, precisamos de um contexto
        lateinit var context: Context

        val ALERT_DIALOG_TITLE: String
            get() = context.getString(R.string.alert_dialog_title)
        val ALERT_DIALOG_MESSAGE: String
            get() = context.getString(R.string.alert_dialog_message)
        val ALERT_DIALOG_POSITIVE: String
            get() = context.getString(R.string.alert_dialog_positive)
        val ALERT_DIALOG_NEGATIVE: String
            get() = context.getString(R.string.alert_dialog_negative)
    }


}

