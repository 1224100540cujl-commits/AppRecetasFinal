package com.recetas.app.ui.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.recetas.app.R
import com.recetas.app.data.model.Notification
import java.text.SimpleDateFormat
import java.util.*

class NotificationAdapter(
    private val onItemClick: (Notification) -> Unit,
    private val onDeleteClick: (Notification) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private var notifications = emptyList<Notification>()

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.notificationCard)
        val titleTextView: TextView = itemView.findViewById(R.id.notificationTitle)
        val messageTextView: TextView = itemView.findViewById(R.id.notificationMessage)
        val timeTextView: TextView = itemView.findViewById(R.id.notificationTime)
        val typeTextView: TextView = itemView.findViewById(R.id.notificationType)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteNotificationButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val currentNotification = notifications[position]

        holder.titleTextView.text = currentNotification.title
        holder.messageTextView.text = currentNotification.message
        holder.typeTextView.text = currentNotification.type

        val dateFormat = SimpleDateFormat("MMM dd, yyyy - HH:mm", Locale.getDefault())
        holder.timeTextView.text = dateFormat.format(currentNotification.triggerTime)

        // Cambiar apariencia si está leída
        if (currentNotification.isRead) {
            holder.cardView.setCardBackgroundColor(
                holder.itemView.context.getColor(android.R.color.white)
            )
            holder.titleTextView.alpha = 0.5f
            holder.messageTextView.alpha = 0.5f
        } else {
            holder.cardView.setCardBackgroundColor(
                holder.itemView.context.getColor(R.color.orange_light)
            )
            holder.titleTextView.alpha = 1.0f
            holder.messageTextView.alpha = 1.0f
        }

        holder.itemView.setOnClickListener {
            onItemClick(currentNotification)
        }

        holder.deleteButton.setOnClickListener {
            onDeleteClick(currentNotification)
        }
    }

    override fun getItemCount() = notifications.size

    fun setNotifications(notifications: List<Notification>) {
        this.notifications = notifications
        notifyDataSetChanged()
    }
}