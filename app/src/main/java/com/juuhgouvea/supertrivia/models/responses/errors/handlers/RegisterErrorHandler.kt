package com.juuhgouvea.supertrivia.models.responses.errors.handlers

import com.juuhgouvea.supertrivia.MainApplication
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.models.responses.errors.RegisterErrorResponse

class RegisterErrorHandler(error: RegisterErrorResponse): ErrorHandler(error) {
    fun getNameError(): String {
        var message: String = ""
        var nameError = (error as RegisterErrorResponse).data.errors.name?.get(0)?.toLowerCase()
        when(nameError) {
            "can't be blank" -> {
                message = MainApplication.getContext().getString(R.string.required_input, MainApplication.getContext().getString(R.string.name))
            }
            else -> {
                message = ""
            }
        }

        return message
    }

    fun getEmailError(): String {
        var message: String = ""
        var emailError = (error as RegisterErrorResponse).data.errors.email?.get(0)?.toLowerCase()
        when(emailError) {
            "can't be blank" -> {
                message = MainApplication.getContext().getString(R.string.required_input, MainApplication.getContext().getString(R.string.email))
            }
            "has already been taken" -> {
                message = MainApplication.getContext().getString(R.string.email_taken)
            }
            "is invalid" -> {
                message = MainApplication.getContext().getString(R.string.invalid_input, MainApplication.getContext().getString(R.string.email))
            }
            else -> {
                message = ""
            }
        }

        return message
    }

    fun getPasswordError(): String {
        var message: String = ""
        var passwordError = (error as RegisterErrorResponse).data.errors.password?.get(0)?.toLowerCase()
        when(passwordError) {
            "can't be blank" -> {
                message = MainApplication.getContext().getString(R.string.required_input, MainApplication.getContext().getString(R.string.password))
            }
            "is too short (minimum is 6 characters)" -> {
                message = MainApplication.getContext().getString(R.string.too_short_input, MainApplication.getContext().getString(R.string.password), 6)
            }
            else -> {
                message = ""
            }
        }

        return message
    }
}