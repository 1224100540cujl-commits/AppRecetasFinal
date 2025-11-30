package com.recetas.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStats(
    @PrimaryKey
    val userId: String = "",
    val recipesCreated: Int = 0,
    val favoritesCount: Int = 0,
    val collectionsCount: Int = 0,
    val daysActive: Int = 0,
    val lastLogin: Long = System.currentTimeMillis()
)