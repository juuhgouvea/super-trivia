package com.juuhgouvea.supertrivia.models.responses.errors.handlers

import com.juuhgouvea.supertrivia.MainApplication
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.models.responses.errors.ErrorResponse

open class ErrorHandler (error: ErrorResponse) {
    protected var error: ErrorResponse = error

    public open fun getMessage(): String {
        var message: String = ""
        when(this.error.message.toLowerCase()) {
            "connection error" -> {
                message = MainApplication.getContext().getString(R.string.connection_error)
            }
            else -> {
                message = ""
            }
        }

        return message
    }
}