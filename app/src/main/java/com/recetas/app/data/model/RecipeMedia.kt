package com.recetas.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_media")
data class RecipeMedia(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int = 0,
    val url: String = "",
    val type: String = "",
    val order: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)