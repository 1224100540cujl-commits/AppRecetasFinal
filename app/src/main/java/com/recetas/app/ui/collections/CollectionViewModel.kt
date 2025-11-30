package com.recetas.app.ui.collections

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.recetas.app.data.local.database.AppDatabase
import com.recetas.app.data.model.RecipeCollection
import com.recetas.app.data.model.RecipeCollectionItem
import com.recetas.app.data.repository.CollectionRepository
import kotlinx.coroutines.launch

class CollectionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CollectionRepository
    val allCollections: LiveData<List<RecipeCollection>>

    init {
        val collectionDao = AppDatabase.getDatabase(application).collectionDao()
        repository = CollectionRepository(collectionDao)
        allCollections = repository.getAllCollections()
    }

    fun createCollection(name: String, description: String) = viewModelScope.launch {
        val collection = RecipeCollection(
            name = name,
            description = description
        )
        repository.createCollection(collection)
    }

    fun deleteCollection(collection: RecipeCollection) = viewModelScope.launch {
        repository.deleteCollection(collection)
    }

    fun addRecipeToCollection(collectionId: Int, recipeId: Int) = viewModelScope.launch {
        repository.addRecipeToCollection(collectionId, recipeId)
    }

    fun getRecipesInCollection(collectionId: Int): LiveData<List<RecipeCollectionItem>> {
        return repository.getRecipesInCollection(collectionId)
    }

    fun removeRecipeFromCollection(collectionId: Int, recipeId: Int) = viewModelScope.launch {
        repository.removeRecipeFromCollection(collectionId, recipeId)
    }
}