package com.example.tellcom.service.constants

class Constants private constructor() {

    object DATABASE {
        const val VERSION_DATABASE = 5
        const val DATABASE_NAME = "orders_database"
    }

    object ENTETY {
        const val PRIMARY_ID = 1
        const val ORDER_COMPLETE = true
        const val ORDER_BROKEN = false
    }

    object NOTIFICATION {
        const val FILLING_IN_FILDS_NOTIFICATION = "Preencha todos os campos!"
        const val SAVED_ORDER = "Ordem salva com sucesso!"
        const val NOT_SAVED_ORDER = "Falha ao salvar ordem!"
        const val INVALID_PROTOCOL_NUMBER = "Número de Protocolo invalido!"
        const val PROTOCOL_NUMBER_REQUERIED = "O número do protocolo deve ter exatamente 8 caracteres"

        const val SAVE_ERROR_TAG = "Save error"
        const val SAVE_ERROR_MESSAGE = "Falha ao salvar a ordem!"

        const val UPDATE_ERROR_TAG = "Update error"
        const val UPDATE_ERROR_MESSAGE = "Falha ao atualizar as ordens!"
    }

    object NAVIGATION {
        const val CARD_VIEW_NOT_CLICKED = 1
        const val DONE_CLICKED = 2
        const val NOT_DONE_CLICKED = 3
    }

}