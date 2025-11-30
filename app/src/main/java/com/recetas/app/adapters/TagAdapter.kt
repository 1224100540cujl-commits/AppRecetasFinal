package com.recetas.app.ui.components.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.recetas.app.R
import com.recetas.app.data.model.Tag

class TagAdapter(
    private val onItemClick: (Tag) -> Unit,
    private val onDeleteClick: ((Tag) -> Unit)? = null,
    private val showDeleteButton: Boolean = true
) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    private var tags = emptyList<Tag>()
    private var recipeCounts = mutableMapOf<Int, Int>()

    class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.tagCard)
        val nameTextView: TextView = itemView.findViewById(R.id.tagName)
        val countTextView: TextView? = itemView.findViewById(R.id.tagCount)
        val deleteButton: ImageButton? = itemView.findViewById(R.id.deleteTagButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val layoutId = if (showDeleteButton) R.layout.item_tag else R.layout.item_tag_small
        val itemView = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return TagViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val currentTag = tags[position]

        holder.nameTextView.text = currentTag.name

        // Aplicar color del tag
        try {
            holder.cardView.setCardBackgroundColor(Color.parseColor(currentTag.color))
        } catch (e: Exception) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF9800"))
        }

        // Solo mostrar contador y botón eliminar si showDeleteButton es true
        if (showDeleteButton && onDeleteClick != null) {
            holder.countTextView?.let {
                val count = recipeCounts[currentTag.id] ?: 0
                it.text = "$count recetas"
                it.visibility = View.VISIBLE
            }

            holder.deleteButton?.visibility = View.VISIBLE
            holder.deleteButton?.setOnClickListener {
                onDeleteClick.invoke(currentTag)
            }
        } else {
            holder.countTextView?.visibility = View.GONE
            holder.deleteButton?.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            onItemClick(currentTag)
        }
    }

    override fun getItemCount() = tags.size

    fun setTags(tags: List<Tag>) {
        this.tags = tags
        notifyDataSetChanged()
    }

    fun setRecipeCounts(counts: Map<Int, Int>) {
        this.recipeCounts = counts.toMutableMap()
        notifyDataSetChanged()
    }
}