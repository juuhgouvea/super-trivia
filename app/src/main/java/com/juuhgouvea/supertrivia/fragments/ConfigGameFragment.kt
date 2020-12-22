package com.juuhgouvea.supertrivia.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.adapters.CategoryAdapter
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

        ArrayAdapter<Difficulty>(requireContext(), android.R.layout.simple_spinner_item, getDifficulties()).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.selectDifficulty.adapter = adapter
        }

        view.listCategories.adapter = categoryAdapter
        view.listCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.btnSaveConfig.setOnClickListener {
            saveConfig(view.selectDifficulty.selectedItem as Difficulty, categoryAdapter.getSelectedCategory())
            navigateToStartGame()
        }

        return view
    }

    fun navigateToStartGame() {
        findNavController().navigate(R.id.navigateToStartGame)
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