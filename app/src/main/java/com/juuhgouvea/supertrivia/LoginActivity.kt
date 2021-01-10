package com.juuhgouvea.supertrivia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.juuhgouvea.supertrivia.dao.UserDAO
import com.juuhgouvea.supertrivia.models.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var loaderModal: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(isLoggedIn()) {
            goToMain()
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        btnRegisterLink.setOnClickListener {
            goToRegister()
        }
        btnLogin.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            loginHandler(email, password)
        }

        this.loaderModal = loaderContainer
    }

    private fun isLoggedIn(): Boolean {
        val token = this.getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("token", "")

        return !token!!.isEmpty()
    }

    private fun goToMain() {
        var intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
    }

    private fun goToRegister() {
        var intent = Intent(this, RegisterActivity::class.java)

        startActivity(intent)
    }

    private fun showLoading(isVisible: Boolean) {
        this.loaderContainer.visibility = if(isVisible) View.VISIBLE else View.GONE
    }

    private fun loginHandler(email: String, password: String) {
        if(email.isBlank() || password.isBlank()) {
            Toast.makeText(this, getString(R.string.blank_credentials), Toast.LENGTH_SHORT)
                .show()
            return
        }

        val userDao = UserDAO()
        showLoading(true)
        userDao.login(User(email, "", password, "")) { response ->
            val loggedUser = response.data.user
            getSharedPreferences("auth", Context.MODE_PRIVATE)
                    .edit().apply {
                        putString("token", loggedUser.token)
                        putString("name", loggedUser.name)
                        putString("email", loggedUser.email)
                    }
                    .apply()

            showLoading(false)
            goToMain()
        }
    }
}