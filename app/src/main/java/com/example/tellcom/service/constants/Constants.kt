package com.example.tellcom.service.constants

class Constants private constructor() {

    object DATABASE {
        const val VERSION_ORDER_DATABASE = 8
        const val DATABASE_ORDER_NAME = "orders_database"

        const val VERSION_SCORE_DATABASE = 2
        const val DATABASE_SCORE_NAME = "score_database"
    }

    object NOTIFICATION {
        const val FILLING_IN_FILDS_NOTIFICATION = "Preencha todos os campos!"
        const val SAVED_ORDER = "Ordem salva com sucesso!"
        const val SAVED_SCORE = "Score salvo com sucesso!"
        const val NOT_SAVED_ORDER = "Falha ao salvar ordem!"
        const val NOT_SAVED_SCORE = "Falha ao salvar o score!"
        const val INVALID_PROTOCOL_NUMBER = "Número de Protocolo invalido!"
        const val PROTOCOL_NUMBER_REQUERIED = "O número do protocolo deve ter exatamente 8 caracteres"

        const val SAVE_ORDER_ERROR_TAG = "Save error"
        const val SAVE_ORDER_ERROR_MESSAGE = "Falha ao salvar a ordem!"

        const val UPDATE_ORDER_ERROR_TAG = "Update error"
        const val UPDATE_ORDER_ERROR_MESSAGE = "Falha ao atualizar as ordens!"

        const val SAVE_SCORE_ERROR_TAG = "Save score error"
        const val SAVE_SCORE_ERROR_MESSAGE = "Falha ao salvar o score!"

        const val UPDATE_SCORE_ERROR_TAG = "Update error"
        const val UPDATE_SCORE_ERROR_MESSAGE = "Falha ao atualizar o score!"
    }


}