package com.recetas.app.data.model

import androidx.room.Entity

@Entity(
    tableName = "recipe_tags",
    primaryKeys = ["recipeId", "tagId"]
)
data class RecipeTag(
    val recipeId: Int = 0,
    val tagId: Int = 0
)