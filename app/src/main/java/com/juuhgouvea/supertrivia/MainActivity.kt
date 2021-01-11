package com.juuhgouvea.supertrivia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.loaderContainer

class MainActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {
    private var loaderModal: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle(getString(R.string.app_name))
        toolbar.setOnMenuItemClickListener(this)

        bottomNavigation.setupWithNavController(findNavController(R.id.navHostFragment))
        this.loaderModal = loaderContainer
    }

    fun showLoading(isVisible: Boolean) {
        this.loaderContainer.visibility = if(isVisible) View.VISIBLE else View.GONE
    }

    fun goToLogin() {
        var intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
    }

    fun logout() {
        this.getSharedPreferences("game", Context.MODE_PRIVATE)
                .edit().apply {
                    clear()
                    commit()
                }
        this.getSharedPreferences("settings", Context.MODE_PRIVATE)
                .edit().apply {
                    clear()
                    commit()
                }
        this.getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit().apply {
                    clear()
                    commit()
                }

        goToLogin()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean = when(item?.itemId) {
        R.id.btnLogout -> {
            logout()

            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}