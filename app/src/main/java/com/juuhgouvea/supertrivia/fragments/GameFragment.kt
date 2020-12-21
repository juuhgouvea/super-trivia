package com.juuhgouvea.supertrivia.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.adapters.AnswerAdapter
import com.juuhgouvea.supertrivia.dao.ProblemDAO
import kotlinx.android.synthetic.main.fragment_game.view.*

class GameFragment : Fragment() {
    private val answerAdapter = AnswerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        view.listAnswer.adapter = answerAdapter
        view.listAnswer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        showProblem(view)

        return view
    }

    // Reference: https://stackoverflow.com/questions/2918920/decode-html-entities-in-android
    fun formatHtml(htmlString: String): String {
        if (Build.VERSION.SDK_INT >= 24)
        {
            return Html.fromHtml(htmlString , Html.FROM_HTML_MODE_LEGACY).toString()
        }
        else
        {
           return Html.fromHtml(htmlString).toString()
        }
    }

    fun hasOpenProblem(): Boolean {
        val hasOpenProblem = requireContext()
            .getSharedPreferences("game", Context.MODE_PRIVATE)
            .getBoolean("has_open_problem", false)

        return hasOpenProblem
    }

    fun showProblem(view: View) {
        val token = requireContext()
            .getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("token", "")

        val problemDao = ProblemDAO()
        if(hasOpenProblem()) {
            problemDao.view(token!!) { response ->
                view.lbProblem.text = formatHtml(response.data.problem.question)
                answerAdapter.updateList(response.data.problem.answers)
            }
        } else {
            problemDao.next(token!!) { response ->
                view.lbProblem.text = formatHtml(response.data.problem.question)
                answerAdapter.updateList(response.data.problem.answers)

                requireContext()
                    .getSharedPreferences("game", Context.MODE_PRIVATE)
                    .edit().apply {
                        putBoolean("has_open_problem", true)
                        apply()
                    }
            }
        }
    }
}