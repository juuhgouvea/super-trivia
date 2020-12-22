package com.juuhgouvea.supertrivia.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.juuhgouvea.supertrivia.R
import kotlinx.android.synthetic.main.fragment_game_over.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class GameOverFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_over, container, false)

        val results = getResults()
        view.lbPlayerName.text = results.get("player")
        view.lbCategoryName.text = getString(R.string.selected_category, results.get("category"))
        view.lbDifficultyName.text = getString(resources.getIdentifier(results.get("difficulty"), "string", requireContext().packageName))
        view.lbStartedAt.text = results.get("started_at")
        view.lbFinishedAt.text = results.get("finished_at")
        view.lbTotalScore.text = getString(R.string.show_score, results.get("score")?.toLong())

        view.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.navigateToNewGame)
        }

        return view
    }

    fun getResults(): HashMap<String, String> {
        val dateFormatter = SimpleDateFormat(getString(R.string.date_pattern))
        val results = HashMap<String, String>()

        val playerName = requireContext()
            .getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("name", "")
        val category = requireContext()
            .getSharedPreferences("settings", Context.MODE_PRIVATE)
            .getString("category_name", "Random")
        val difficulty = requireContext()
            .getSharedPreferences("settings", Context.MODE_PRIVATE)
            .getString("difficulty", "")
        val startedAt = requireContext()
            .getSharedPreferences("game", Context.MODE_PRIVATE)
            .getString("started_at", "")
        val finishedAt = requireContext()
            .getSharedPreferences("game", Context.MODE_PRIVATE)
            .getString("finished_at", "")
        val score = requireContext()
            .getSharedPreferences("game", Context.MODE_PRIVATE)
            .getLong("score", 0)

        results.apply {
            put("player", playerName!!)
            put("category", category!!)
            put("difficulty", difficulty!!)
            put("started_at", dateFormatter.format(Date(startedAt)))
            put("finished_at", dateFormatter.format(Date(finishedAt)))
            put("score", score.toString())
        }

        return results
    }
}