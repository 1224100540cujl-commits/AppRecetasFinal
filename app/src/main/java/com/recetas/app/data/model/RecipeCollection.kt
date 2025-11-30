package com.recetas.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_collections")
data class RecipeCollection(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val createdAt: Long = System.currentTimeMillis()
)