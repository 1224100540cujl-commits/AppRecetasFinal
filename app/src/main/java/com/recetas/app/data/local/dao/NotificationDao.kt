package com.recetas.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.recetas.app.data.model.Notification

@Dao
interface NotificationDao {

    @Insert
    suspend fun insert(notification: Notification)

    @Update
    suspend fun update(notification: Notification)

    @Delete
    suspend fun delete(notification: Notification)

    @Query("SELECT * FROM notifications ORDER BY triggerTime DESC")
    fun getAllNotifications(): LiveData<List<Notification>>

    @Query("SELECT * FROM notifications WHERE isRead = 0 ORDER BY triggerTime DESC")
    fun getUnreadNotifications(): LiveData<List<Notification>>

    @Query("SELECT COUNT(*) FROM notifications WHERE isRead = 0")
    suspend fun getUnreadCount(): Int

    @Query("UPDATE notifications SET isRead = 1 WHERE id = :notificationId")
    suspend fun markAsRead(notificationId: Int)

    @Query("DELETE FROM notifications WHERE isRead = 1")
    suspend fun deleteReadNotifications()
}