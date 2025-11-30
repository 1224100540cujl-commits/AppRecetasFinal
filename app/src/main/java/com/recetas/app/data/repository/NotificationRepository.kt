package com.recetas.app.data.repository

import androidx.lifecycle.LiveData
import com.recetas.app.data.local.dao.NotificationDao
import com.recetas.app.data.model.Notification

class NotificationRepository(private val notificationDao: NotificationDao) {

    fun getAllNotifications(): LiveData<List<Notification>> {
        return notificationDao.getAllNotifications()
    }

    fun getUnreadNotifications(): LiveData<List<Notification>> {
        return notificationDao.getUnreadNotifications()
    }

    suspend fun insert(notification: Notification) {
        notificationDao.insert(notification)
    }

    suspend fun update(notification: Notification) {
        notificationDao.update(notification)
    }

    suspend fun delete(notification: Notification) {
        notificationDao.delete(notification)
    }

    suspend fun markAsRead(notificationId: Int) {
        notificationDao.markAsRead(notificationId)
    }

    suspend fun getUnreadCount(): Int {
        return notificationDao.getUnreadCount()
    }

    suspend fun deleteReadNotifications() {
        notificationDao.deleteReadNotifications()
    }
}