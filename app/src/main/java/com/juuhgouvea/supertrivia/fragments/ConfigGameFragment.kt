package com.juuhgouvea.supertrivia.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.adapters.CategoryAdapter
import com.juuhgouvea.supertrivia.dao.GameDAO
import com.juuhgouvea.supertrivia.models.Category
import com.juuhgouvea.supertrivia.models.Difficulty
import kotlinx.android.synthetic.main.fragment_config_game.view.*

class ConfigGameFragment : Fragment() {
    private val categoryAdapter = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_config_game, container, false)

        val difficultyAdapter = ArrayAdapter<Difficulty>(requireContext(), android.R.layout.simple_spinner_item, getDifficulties()).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.selectDifficulty.adapter = adapter
        }

        val selectedDifficulty = getSavedDifficulty()
        val selectedCategory = getSavedCategory()

        if(selectedDifficulty != null) {
            view.selectDifficulty.setSelection(difficultyAdapter.getPosition(selectedDifficulty))
        }

        categoryAdapter.setSelectedCategory(selectedCategory)

        view.listCategories.adapter = categoryAdapter
        view.listCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.btnSaveConfig.setOnClickListener {
            saveConfig(view.selectDifficulty.selectedItem as Difficulty, categoryAdapter.getSelectedCategory())
            startGame(view.selectDifficulty.selectedItem as Difficulty, categoryAdapter.getSelectedCategory())
        }

        return view
    }

    fun navigateToGame() {
        findNavController().navigate(R.id.navigateToGame)
    }

    fun getSavedCategory(): Category? {
        val categoryId = requireContext()
                .getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getLong("category_id", -1)

        val categoryName = requireContext()
                .getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getString("category_name", "")

        if(categoryId.toInt() === -1) {
            return null
        }

        return Category(categoryId, categoryName!!)
    }

   fun getSavedDifficulty(): Difficulty? {
        val difficultyValue = requireContext()
                .getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getString("difficulty", "")

       if(difficultyValue!!.isEmpty()) {
           return null
       }

       val difficultyStringResId = resources.getIdentifier(difficultyValue, "string", requireContext().packageName)
       return Difficulty(getString(difficultyStringResId), difficultyValue)
    }

    fun getToken(): String {
        val token = requireContext()
                .getSharedPreferences("auth", Context.MODE_PRIVATE)
                .getString("token", "")

        return token!!
    }

    fun startGame(difficulty: Difficulty, category: Category?) {
        val gameDao = GameDAO()
        gameDao.start(getToken(), difficulty.value, category?.id.toString()) { response ->
            requireContext()
                .getSharedPreferences("game", Context.MODE_PRIVATE)
                .edit()
                .apply {
                    putString("status", response.data.game.status)
                    putString("started_at", response.data.game.startedAt.toString())
                    putLong("score", response.data.game.score)

                    apply()
                }

            navigateToGame()
        }

    }

    fun saveConfig(selectedDifficulty: Difficulty, selectedCategory: Category?) {
        requireContext()
            .getSharedPreferences("settings", Context.MODE_PRIVATE)
            .edit().apply {
                if(selectedCategory != null) {
                    putLong("category_id", selectedCategory.id)
                    putString("category_name", selectedCategory.name)
                } else {
                    remove("category_id")
                    putString("category_name", "Random")
                }
                putString("difficulty", selectedDifficulty.value)
                apply()
            }
    }

    fun getDifficulties(): List<Difficulty> {
        val difficulties = listOf<Difficulty>(
            Difficulty(getString(R.string.easy), "easy"),
            Difficulty(getString(R.string.medium), "medium"),
            Difficulty(getString(R.string.hard), "hard")
        )

        return difficulties
    }
}