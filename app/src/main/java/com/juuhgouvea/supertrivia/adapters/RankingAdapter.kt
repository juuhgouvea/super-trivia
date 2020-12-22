package com.juuhgouvea.supertrivia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.dao.RankingDAO
import com.juuhgouvea.supertrivia.models.RankingPosition
import kotlinx.android.synthetic.main.item_ranking.view.*

class RankingAdapter: RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    private var rankingDao = RankingDAO()
    private var ranking: List<RankingPosition> = listOf()

    override fun getItemCount() = ranking.size

    init {
        rankingDao.getAll { response ->
            ranking = response.data.ranking
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_ranking, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rankingPosition = ranking[position]
        holder.fillView(rankingPosition)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun fillView(rankingPosition: RankingPosition) {
            itemView.lbPosition.text = rankingPosition.user
            itemView.lbRankingScore.text = rankingPosition.score.toString()
        }
    }
}