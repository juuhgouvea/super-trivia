package com.juuhgouvea.supertrivia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.juuhgouvea.supertrivia.dao.UserDAO
import com.juuhgouvea.supertrivia.models.User
import com.juuhgouvea.supertrivia.models.responses.errors.ErrorResponse
import com.juuhgouvea.supertrivia.models.responses.errors.RegisterErrorResponse
import com.juuhgouvea.supertrivia.models.responses.errors.handlers.ErrorHandler
import com.juuhgouvea.supertrivia.models.responses.errors.handlers.RegisterErrorHandler
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.loaderContainer

class RegisterActivity : AppCompatActivity() {
    private var loaderModal: View? = null
    private var nameError: TextView? = null
    private var emailError: TextView? = null
    private var passwordError: TextView? = null
    private var confirmError: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            val name = inputName.text.toString()
            val email = inputRegisterEmail.text.toString()
            val password = inputRegisterPassword.text.toString()
            val confirmPassword = inputConfirmPassword.text.toString()

            handleRegister(name, email, password, confirmPassword)
        }

        this.loaderModal = loaderContainer
        this.nameError = nameErrorMessage
        this.emailError = emailErrorMessage
        this.passwordError = passwordErrorMessage
        this.confirmError = confirmErrorMessage
    }

    private fun showLoading(isVisible: Boolean) {
        this.loaderContainer.visibility = if(isVisible) View.VISIBLE else View.GONE
    }

    fun handleRegister(name: String, email: String, password: String, confirmPassword: String) {
        this.nameError?.visibility = View.GONE
        this.emailError?.visibility = View.GONE
        this.passwordError?.visibility = View.GONE
        this.confirmError?.visibility = View.GONE

        if(!password.equals(confirmPassword)) {
            this.confirmError?.visibility = View.VISIBLE
            this.confirmError?.text = getString(R.string.password_not_match)
            return
        }

        val userDao = UserDAO()

        showLoading(true)
        userDao.register(User(email, name, password, ""), { response ->
                val loggedUser = response.data.user
                getSharedPreferences("auth", Context.MODE_PRIVATE)
                        .edit().apply {
                            putString("token", loggedUser.token)
                            putString("name", loggedUser.name)
                            putString("email", loggedUser.email)
                        }
                        .apply()

                var intent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
            showLoading(false)
            startActivity(intent)
            },
            { error: ErrorResponse? ->
                val errorHandler = RegisterErrorHandler(error as RegisterErrorResponse)
                val nameError = errorHandler.getNameError()
                val emailError = errorHandler.getEmailError()
                val passwordError = errorHandler.getPasswordError()

                showLoading(false)
                if(!nameError.isEmpty()) {
                    this.nameError?.visibility = View.VISIBLE
                    this.nameError?.text = nameError
                }
                if(!emailError.isEmpty()) {
                    this.emailError?.visibility = View.VISIBLE
                    this.emailError?.text = emailError
                }
                if(!passwordError.isEmpty()) {
                    this.passwordError?.visibility = View.VISIBLE
                    this.passwordError?.text = passwordError
                }
            })
    }
}