package com.recetas.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_items_mejorado")
data class ShoppingListItemMejorado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ingredientName: String = "",
    val quantity: String = "",
    val unit: String = "",
    val estimatedPrice: Double = 0.0,
    val category: String = "",
    val isChecked: Boolean = false
)