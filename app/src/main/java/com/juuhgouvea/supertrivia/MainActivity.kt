package com.juuhgouvea.supertrivia

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isConfigured()) {
            if(isPlaying()) {
                findNavController(R.id.navHostFragment).popBackStack(R.id.startGameFragment, true)
                findNavController(R.id.navHostFragment).popBackStack(R.id.configGameFragment, true)
                findNavController(R.id.navHostFragment).navigate(R.id.gameFragment)
            } else {
                findNavController(R.id.navHostFragment).popBackStack(R.id.configGameFragment, true)
                findNavController(R.id.navHostFragment).navigate(R.id.startGameFragment)
            }
        }
    }

    fun isPlaying(): Boolean {
        val gameStatus = this
                .getSharedPreferences("game", Context.MODE_PRIVATE)
                .getString("status", "")

        return !gameStatus!!.equals("finished")
    }

    fun isConfigured(): Boolean {
        val categoryName = this
                .getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getString("category_name", "")

        val difficulty = this
                .getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getString("difficulty", "")

        return !categoryName!!.isEmpty() && !difficulty!!.isEmpty()
    }
}