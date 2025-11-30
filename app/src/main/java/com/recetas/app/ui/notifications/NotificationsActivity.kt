package com.recetas.app.ui.notifications

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.recetas.app.R
import com.recetas.app.data.model.Notification
import com.recetas.app.databinding.ActivityNotificationsBinding
import com.recetas.app.ui.components.adapters.NotificationAdapter
import java.text.SimpleDateFormat
import java.util.*

class NotificationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationsBinding
    private lateinit var viewModel: NotificationViewModel
    private lateinit var adapter: NotificationAdapter
    private var selectedDateTime: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NotificationViewModel::class.java]

        setupRecyclerView()
        observeNotifications()

        binding.fabAddNotification.setOnClickListener {
            showCreateNotificationDialog()
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.clearReadButton.setOnClickListener {
            showClearReadDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = NotificationAdapter(
            onItemClick = { notification ->
                toggleReadStatus(notification)
            },
            onDeleteClick = { notification ->
                showDeleteDialog(notification)
            }
        )

        binding.notificationsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notificationsRecyclerView.adapter = adapter
    }

    private fun observeNotifications() {
        viewModel.allNotifications.observe(this) { notifications ->
            if (notifications.isEmpty()) {
                binding.emptyStateLayout.visibility = View.VISIBLE
                binding.notificationsRecyclerView.visibility = View.GONE
            } else {
                binding.emptyStateLayout.visibility = View.GONE
                binding.notificationsRecyclerView.visibility = View.VISIBLE
                adapter.setNotifications(notifications)
            }
        }
    }

    private fun showCreateNotificationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nueva Notificación")

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val titleInput = EditText(this).apply {
            hint = "Título"
        }

        val messageInput = EditText(this).apply {
            hint = "Mensaje"
            minLines = 3
        }

        val dateTimeText = TextView(this).apply {
            text = "Seleccionar fecha y hora"
            textSize = 14f
            setTextColor(getColor(R.color.orange_primary))
            setPadding(0, 30, 0, 10)
            setOnClickListener {
                showDateTimePicker(this)
            }
        }

        // Inicializar con fecha/hora actual + 1 hora
        selectedDateTime = Calendar.getInstance()
        selectedDateTime.add(Calendar.HOUR, 1)
        updateDateTimeText(dateTimeText)

        layout.addView(titleInput)
        layout.addView(messageInput)
        layout.addView(dateTimeText)
        builder.setView(layout)

        builder.setPositiveButton("Crear") { _, _ ->
            val title = titleInput.text.toString().trim()
            val message = messageInput.text.toString().trim()

            if (title.isNotEmpty() && message.isNotEmpty()) {
                val notification = Notification(
                    title = title,
                    message = message,
                    triggerTime = selectedDateTime.time,
                    type = "REMINDER",
                    isRead = false,
                    createdAt = Date()
                )
                viewModel.addNotification(notification)
                Toast.makeText(this, "Notificación creada ✅", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun showDateTimePicker(textView: TextView) {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDateTime.set(Calendar.YEAR, year)
                selectedDateTime.set(Calendar.MONTH, month)
                selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, hourOfDay, minute ->
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        selectedDateTime.set(Calendar.MINUTE, minute)
                        updateDateTimeText(textView)
                    },
                    selectedDateTime.get(Calendar.HOUR_OF_DAY),
                    selectedDateTime.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
            },
            selectedDateTime.get(Calendar.YEAR),
            selectedDateTime.get(Calendar.MONTH),
            selectedDateTime.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateDateTimeText(textView: TextView) {
        val dateFormat = SimpleDateFormat("MMM dd, yyyy - HH:mm", Locale.getDefault())
        textView.text = "📅 ${dateFormat.format(selectedDateTime.time)}"
    }

    private fun toggleReadStatus(notification: Notification) {
        if (notification.isRead) {
            notification.isRead = false
            viewModel.updateNotification(notification)
        } else {
            notification.isRead = true
            viewModel.updateNotification(notification)
        }
    }

    private fun showDeleteDialog(notification: Notification) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Notificación")
            .setMessage("¿Estás seguro?")
            .setPositiveButton("Eliminar") { _, _ ->
                viewModel.deleteNotification(notification)
                Toast.makeText(this, "Notificación eliminada", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun showClearReadDialog() {
        AlertDialog.Builder(this)
            .setTitle("Limpiar Notificaciones")
            .setMessage("¿Eliminar todas las notificaciones leídas?")
            .setPositiveButton("Eliminar") { _, _ ->
                viewModel.deleteReadNotifications()
                Toast.makeText(this, "Notificaciones limpiadas", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}