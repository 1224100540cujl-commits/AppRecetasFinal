package com.recetas.app.ui.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.recetas.app.R
import com.recetas.app.data.model.RecipeMedia

class RecipeMediaAdapter(
    private val onItemClick: (RecipeMedia) -> Unit,
    private val onDeleteClick: (RecipeMedia) -> Unit
) : RecyclerView.Adapter<RecipeMediaAdapter.MediaViewHolder>() {

    private var mediaList = emptyList<RecipeMedia>()

    class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.mediaCard)
        val imageView: ImageView = itemView.findViewById(R.id.mediaImageView)
        val typeTextView: TextView = itemView.findViewById(R.id.mediaType)
        val urlTextView: TextView = itemView.findViewById(R.id.mediaUrl)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteMediaButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_media, parent, false)
        return MediaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val currentMedia = mediaList[position]

        holder.typeTextView.text = currentMedia.type
        holder.urlTextView.text = currentMedia.url

        // Cargar imagen con Glide
        Glide.with(holder.itemView.context)
            .load(currentMedia.url)
            .placeholder(R.drawable.placeholder_image) // Placeholder mientras carga
            .error(R.drawable.error_image) // Imagen si hay error
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            onItemClick(currentMedia)
        }

        holder.deleteButton.setOnClickListener {
            onDeleteClick(currentMedia)
        }
    }

    override fun getItemCount() = mediaList.size

    fun setMedia(media: List<RecipeMedia>) {
        this.mediaList = media
        notifyDataSetChanged()
    }
}