package com.juuhgouvea.supertrivia.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.models.Difficulty
import kotlinx.android.synthetic.main.fragment_config_game.*
import kotlinx.android.synthetic.main.fragment_config_game.view.*

class ConfigGameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_config_game, container, false)

        ArrayAdapter<Difficulty>(requireContext(), android.R.layout.simple_spinner_item, getDifficulties()).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view.selectDifficulty.adapter = adapter
        }

        return view
    }

    fun getDifficulties(): List<Difficulty> {
        val difficultiesText = resources.getStringArray(R.array.difficulty)
        val difficulties = listOf<Difficulty>(
            Difficulty(difficultiesText[0], "easy"),
            Difficulty(difficultiesText[1], "medium"),
            Difficulty(difficultiesText[2], "hard")
        )

        return difficulties
    }
}