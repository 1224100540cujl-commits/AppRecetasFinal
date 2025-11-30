package com.recetas.app.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.recetas.app.data.local.database.AppDatabase
import com.recetas.app.data.model.Rating
import com.recetas.app.data.repository.RatingRepository
import kotlinx.coroutines.launch

class RatingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RatingRepository

    init {
        val ratingDao = AppDatabase.getDatabase(application).ratingDao()
        repository = RatingRepository(ratingDao)
    }

    fun getRatingsByRecipe(recipeId: Int): LiveData<List<Rating>> {
        return repository.getRatingsByRecipe(recipeId)
    }

    fun addRating(rating: Rating) = viewModelScope.launch {
        repository.insert(rating)
    }

    suspend fun getAverageRating(recipeId: Int): Float {
        return repository.getAverageRating(recipeId)
    }

    suspend fun getRatingsCount(recipeId: Int): Int {
        return repository.getRatingsCount(recipeId)
    }
}