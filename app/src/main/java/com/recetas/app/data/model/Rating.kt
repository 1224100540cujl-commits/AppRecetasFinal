package com.recetas.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ratings")
data class Rating(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int = 0,
    val userName: String = "",
    val stars: Int = 0,
    val comment: String = "",
    val createdAt: Long = System.currentTimeMillis()
)