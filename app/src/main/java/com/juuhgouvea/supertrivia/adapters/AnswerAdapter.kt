package com.juuhgouvea.supertrivia.adapters

import android.os.Build
import android.text.Html
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

    fun getSelectedAnswer(): Answer? {
        return this.selectedAnswer
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
            itemView.rbAnswer.text = formatHtml(answer.description)

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