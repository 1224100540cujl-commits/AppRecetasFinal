package com.recetas.app.data.repository

import androidx.lifecycle.LiveData
import com.recetas.app.data.local.dao.CollectionDao
import com.recetas.app.data.model.RecipeCollection
import com.recetas.app.data.model.RecipeCollectionItem

class CollectionRepository(private val collectionDao: CollectionDao) {

    fun getAllCollections(): LiveData<List<RecipeCollection>> {
        return collectionDao.getAllCollections()
    }

    suspend fun createCollection(collection: RecipeCollection): Long {
        return collectionDao.insertCollection(collection)
    }

    suspend fun deleteCollection(collection: RecipeCollection) {
        collectionDao.deleteCollection(collection)
    }

    suspend fun addRecipeToCollection(collectionId: Int, recipeId: Int) {
        val item = RecipeCollectionItem(
            collectionId = collectionId,
            recipeId = recipeId
        )
        collectionDao.addRecipeToCollection(item)
    }

    fun getRecipesInCollection(collectionId: Int): LiveData<List<RecipeCollectionItem>> {
        return collectionDao.getRecipesInCollection(collectionId)
    }

    suspend fun removeRecipeFromCollection(collectionId: Int, recipeId: Int) {
        collectionDao.removeRecipeFromCollection(collectionId, recipeId)
    }
}