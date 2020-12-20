package com.juuhgouvea.supertrivia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.dao.CategoryDAO
import com.juuhgouvea.supertrivia.models.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var categoryDAO = CategoryDAO()
    private var selectedCategory: Category? = null
    private var categories: List<Category> = listOf()

    init {
        categoryDAO.getAll { response ->
            this.categories = response.data.categories
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = categories.size

    override fun getItemViewType(position: Int): Int {
        val category = categories[position]
        return if(category.equals(selectedCategory)) R.layout.item_category_selected else R.layout.item_category
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.fillView(category)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun fillView(category: Category) {
            itemView.lbCategory.text = category.name

            itemView.setOnClickListener {
                var lastSelected = selectedCategory
                if(lastSelected != null) {
                    selectedCategory = null
                    notifyItemChanged(categories.indexOf(lastSelected))
                }

                if(!category.equals(lastSelected)) {
                    selectedCategory = category
                }

                notifyItemChanged(categories.indexOf(category))
            }
        }
    }
}