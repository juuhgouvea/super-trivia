package com.juuhgouvea.supertrivia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.juuhgouvea.supertrivia.dao.UserDAO
import com.juuhgouvea.supertrivia.models.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
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
    }

    fun handleRegister(name: String, email: String, password: String, confirmPassword: String) {
        if(!password.equals(confirmPassword)) {
            Toast.makeText(this, getString(R.string.password_not_match), Toast.LENGTH_SHORT)
                .show()

            return
        }

        val userDao = UserDAO()

        userDao.register(User(email, name, password, "")) { response ->
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

            startActivity(intent)
        }
    }
}