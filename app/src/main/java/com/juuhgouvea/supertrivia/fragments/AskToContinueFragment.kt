package com.juuhgouvea.supertrivia.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.juuhgouvea.supertrivia.MainActivity
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.dao.GameDAO
import kotlinx.android.synthetic.main.fragment_ask_to_continue.view.*
import java.util.*

class AskToContinueFragment : Fragment() {
    private val gameDao = GameDAO()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ask_to_continue, container, false)
        val isCorrect = arguments?.getBoolean("isCorrect")
        val score = arguments?.getLong("score")

        view.lbIsCorrect.text = getString(if(isCorrect!!) R.string.correct_answer else R.string.incorrect_answer)
        view.lbScore.text = getString(R.string.show_score, score)
        view.btnNextProblem.setOnClickListener { goToNextProblem() }
        view.btnFinishGame.setOnClickListener {
            finishGame()
        }

        return view
    }

    fun getToken(): String {
        return requireContext()
            .getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("token", "")!!
    }

    fun saveResults(status: String, startedAt: Date, finishedAt: Date, score: Long) {
        requireContext()
            .getSharedPreferences("game", Context.MODE_PRIVATE)
            .edit().apply {
                putString("status", status)
                putString("started_at", startedAt.toString())
                putString("finished_at", finishedAt.toString())
                putLong("score", score)

                apply()
            }
    }

    fun finishGame() {
        (activity as MainActivity).showLoading(true)
        gameDao.finish(getToken()) { response ->
            (activity as MainActivity).showLoading(false)
            saveResults(
                response.data.game.status,
                response.data.game.startedAt,
                response.data.game.finishedAt,
                response.data.game.score
            )

            goToResume()
        }
    }

    fun goToResume() {
        findNavController().navigate(R.id.navigateToGameOver)
    }

    fun goToNextProblem() {
        findNavController().navigate(R.id.navigateToResumeGame)
    }
}