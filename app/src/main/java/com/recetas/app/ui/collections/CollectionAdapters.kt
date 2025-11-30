package com.recetas.app.ui.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.recetas.app.R
import com.recetas.app.data.model.RecipeCollection

class CollectionAdapter(
    private val onItemClick: (RecipeCollection) -> Unit,
    private val onDeleteClick: (RecipeCollection) -> Unit
) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var collections = emptyList<RecipeCollection>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.collectionName)
        val descText: TextView = view.findViewById(R.id.collectionDescription)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_collection, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val collection = collections[position]
        holder.nameText.text = collection.name
        holder.descText.text = collection.description

        holder.itemView.setOnClickListener { onItemClick(collection) }
        holder.deleteButton.setOnClickListener { onDeleteClick(collection) }
    }

    override fun getItemCount() = collections.size

    fun setCollections(collections: List<RecipeCollection>) {
        this.collections = collections
        notifyDataSetChanged()
    }
}