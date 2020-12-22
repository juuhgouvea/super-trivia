package com.juuhgouvea.supertrivia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.adapters.RankingAdapter
import kotlinx.android.synthetic.main.fragment_ranking.view.*

class RankingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_ranking, container, false)

        val rankingAdapter = RankingAdapter()
        view.listRanking.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.listRanking.adapter = rankingAdapter

        return view
    }
}