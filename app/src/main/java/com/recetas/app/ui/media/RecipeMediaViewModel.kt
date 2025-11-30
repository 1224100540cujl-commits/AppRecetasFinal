package com.recetas.app.ui.media

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.recetas.app.data.local.database.AppDatabase
import com.recetas.app.data.model.RecipeMedia
import com.recetas.app.data.repository.RecipeMediaRepository
import kotlinx.coroutines.launch

class RecipeMediaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeMediaRepository

    init {
        val mediaDao = AppDatabase.getDatabase(application).recipeMediaDao()
        repository = RecipeMediaRepository(mediaDao)
    }

    fun getMediaByRecipe(recipeId: Int): LiveData<List<RecipeMedia>> {
        return repository.getMediaByRecipe(recipeId)
    }

    fun addMedia(recipeId: Int, url: String, type: String, order: Int) = viewModelScope.launch {
        repository.addMedia(recipeId, url, type, order)
    }

    fun updateMedia(media: RecipeMedia) = viewModelScope.launch {
        repository.update(media)
    }

    fun deleteMedia(media: RecipeMedia) = viewModelScope.launch {
        repository.delete(media)
    }

    suspend fun getMediaCount(recipeId: Int): Int {
        return repository.getMediaCount(recipeId)
    }
}