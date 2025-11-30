package com.recetas.app.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.recetas.app.data.local.database.AppDatabase
import com.recetas.app.data.model.Notification
import com.recetas.app.data.repository.NotificationRepository
import kotlinx.coroutines.launch

class NotificationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotificationRepository
    val allNotifications: LiveData<List<Notification>>
    val unreadNotifications: LiveData<List<Notification>>

    init {
        val notificationDao = AppDatabase.getDatabase(application).notificationDao()
        repository = NotificationRepository(notificationDao)
        allNotifications = repository.getAllNotifications()
        unreadNotifications = repository.getUnreadNotifications()
    }

    fun addNotification(notification: Notification) = viewModelScope.launch {
        repository.insert(notification)
    }

    fun updateNotification(notification: Notification) = viewModelScope.launch {
        repository.update(notification)
    }

    fun deleteNotification(notification: Notification) = viewModelScope.launch {
        repository.delete(notification)
    }

    fun markAsRead(notificationId: Int) = viewModelScope.launch {
        repository.markAsRead(notificationId)
    }

    suspend fun getUnreadCount(): Int {
        return repository.getUnreadCount()
    }

    fun deleteReadNotifications() = viewModelScope.launch {
        repository.deleteReadNotifications()
    }
}