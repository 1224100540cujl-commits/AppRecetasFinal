package com.recetas.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.recetas.app.R
import com.recetas.app.data.model.Rating
import java.text.SimpleDateFormat
import java.util.*

class RatingAdapter : RecyclerView.Adapter<RatingAdapter.ViewHolder>() {

    private var ratings = emptyList<Rating>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.ratingUserName)
        val stars: RatingBar = view.findViewById(R.id.ratingStars)
        val date: TextView = view.findViewById(R.id.ratingDate)
        val comment: TextView = view.findViewById(R.id.ratingComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rating, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rating = ratings[position]
        holder.userName.text = rating.userName
        holder.stars.rating = rating.stars.toFloat()
        holder.comment.text = rating.comment
        holder.date.text = getTimeAgo(rating.createdAt)
    }

    override fun getItemCount() = ratings.size

    fun setRatings(ratings: List<Rating>) {
        this.ratings = ratings
        notifyDataSetChanged()
    }

    private fun getTimeAgo(timestamp: Long): String {
        val diff = System.currentTimeMillis() - timestamp
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            days > 0 -> "Hace $days día${if (days > 1) "s" else ""}"
            hours > 0 -> "Hace $hours hora${if (hours > 1) "s" else ""}"
            minutes > 0 -> "Hace $minutes minuto${if (minutes > 1) "s" else ""}"
            else -> "Hace unos segundos"
        }
    }
}