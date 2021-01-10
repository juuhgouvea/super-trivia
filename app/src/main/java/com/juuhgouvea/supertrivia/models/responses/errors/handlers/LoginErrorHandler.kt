package com.juuhgouvea.supertrivia.models.responses.errors.handlers

import com.juuhgouvea.supertrivia.MainApplication
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.models.responses.errors.ErrorResponse

class LoginErrorHandler(error: ErrorResponse): ErrorHandler(error) {
    override fun getMessage(): String {
        var message = super.getMessage()

        if(!message.isEmpty()) {
            return message
        }

        when(this.error.message.toLowerCase()) {
            "invalid email or password" -> {
                message = MainApplication.getContext().getString(R.string.invalid_credentials)
            }
            else -> {
                message = ""
            }
        }
        return message
    }
}