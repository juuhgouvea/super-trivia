package com.juuhgouvea.supertrivia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.models.Answer
import kotlinx.android.synthetic.main.item_answer.view.*

class AnswerAdapter: RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    private var selectedAnswer: Answer? = null
    private var selectedView: View? = null
    private var answers = listOf<Answer>()

    fun updateList(answers: List<Answer>) {
        this.answers = answers
        notifyDataSetChanged()
    }

    override fun getItemCount() = answers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_answer, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = answers[position]
        holder.fillView(answer)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun fillView(answer: Answer) {
            itemView.rbAnswer.text = answer.description

            itemView.rbAnswer.setOnClickListener { view ->
                if(selectedAnswer != null) {
                    if(!selectedAnswer!!.equals(answer)) {
                        selectedView?.rbAnswer?.isChecked = false
                    }
                }

                selectedAnswer = answer
                selectedView = view
            }
        }
    }
}