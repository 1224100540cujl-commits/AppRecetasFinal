package com.recetas.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_collection_items")
data class RecipeCollectionItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val collectionId: Int = 0,
    val recipeId: Int = 0,
    val addedAt: Long = System.currentTimeMillis()
)