package com.juuhgouvea.supertrivia.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.juuhgouvea.supertrivia.R
import kotlinx.android.synthetic.main.fragment_ask_to_continue.view.*

class AskToContinueFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ask_to_continue, container, false)
        val isCorrect = arguments?.getBoolean("isCorrect")
        val score = arguments?.getLong("score")
        
        view.lbIsCorrect.text = getString(if(isCorrect!!) R.string.correct_answer else R.string.incorrect_answer)
        view.lbScore.text = getString(R.string.show_score, score)
        view.btnNextProblem.setOnClickListener { goToNextProblem() }

        return view
    }

    fun goToNextProblem() {
        findNavController().navigate(R.id.navigateToResumeGame)
    }
}