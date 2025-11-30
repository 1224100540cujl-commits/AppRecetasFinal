package com.recetas.app.data.repository

import androidx.lifecycle.LiveData
import com.recetas.app.data.local.dao.RatingDao
import com.recetas.app.data.model.Rating

class RatingRepository(private val ratingDao: RatingDao) {

    fun getRatingsByRecipe(recipeId: Int): LiveData<List<Rating>> {
        return ratingDao.getRatingsByRecipe(recipeId)
    }

    suspend fun insert(rating: Rating) {
        ratingDao.insert(rating)
    }

    suspend fun getAverageRating(recipeId: Int): Float {
        return ratingDao.getAverageRating(recipeId) ?: 0f
    }

    suspend fun getRatingsCount(recipeId: Int): Int {
        return ratingDao.getRatingsCount(recipeId)
    }
}