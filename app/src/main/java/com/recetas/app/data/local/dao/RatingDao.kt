package com.recetas.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.recetas.app.data.model.Rating

@Dao
interface RatingDao {
    @Insert
    suspend fun insert(rating: Rating)

    @Query("SELECT * FROM ratings WHERE recipeId = :recipeId ORDER BY createdAt DESC")
    fun getRatingsByRecipe(recipeId: Int): LiveData<List<Rating>>

    @Query("SELECT AVG(stars) FROM ratings WHERE recipeId = :recipeId")
    suspend fun getAverageRating(recipeId: Int): Float?

    @Query("SELECT COUNT(*) FROM ratings WHERE recipeId = :recipeId")
    suspend fun getRatingsCount(recipeId: Int): Int
}