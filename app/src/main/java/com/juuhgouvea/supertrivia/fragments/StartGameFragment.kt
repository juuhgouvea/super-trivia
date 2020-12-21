package com.juuhgouvea.supertrivia.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.dao.GameDAO
import com.juuhgouvea.supertrivia.models.Category
import com.juuhgouvea.supertrivia.models.Difficulty
import com.juuhgouvea.supertrivia.models.responses.GameResponse
import kotlinx.android.synthetic.main.fragment_start_game.view.*

class StartGameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_game, container, false)

        val(difficulty, category) = getSettings()
        view.lbSelectedCategory.text = getString(R.string.selected_category, category?.name)
        view.lbSelectedDifficulty.text = getString(R.string.selected_difficulty, difficulty?.name)
        view.btnStartGame.setOnClickListener {
            startGame()
            navigateToGame()
        }

        return view
    }

    fun navigateToGame() {
        findNavController().navigate(R.id.navigateToGame)
    }

    fun startGame() {
        val gameDao = GameDAO()

        val token = requireContext()
            .getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("token", "")
        val (difficulty, category) = getSettings()

        gameDao.start(token!!, difficulty!!.value, category?.id.toString()) { response ->
            requireContext()
                .getSharedPreferences("game", Context.MODE_PRIVATE)
                .edit()
                .apply {
                    putString("status", response.data.game.status)
                    putString("started_at", response.data.game.startedAt.toString())
                    putLong("score", response.data.game.score)

                    apply()
                }
        }
    }

    fun getSettings(): Pair<Difficulty?, Category?> {
        val settingsPreference = requireContext()
            .getSharedPreferences("settings", Context.MODE_PRIVATE)

        val difficultyValue = settingsPreference.getString("difficulty_value", "easy")
        val difficultyName = settingsPreference.getString("difficulty_name", "")
        val categoryId = settingsPreference.getLong("category_id", -1)
        val categoryName = settingsPreference.getString("category_name", "")

        return Pair(
            Difficulty(difficultyName!!, difficultyValue!!),
            Category(categoryId, categoryName!!)
        )
    }
}