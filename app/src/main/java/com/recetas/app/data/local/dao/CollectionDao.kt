package com.recetas.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.recetas.app.data.model.RecipeCollection
import com.recetas.app.data.model.RecipeCollectionItem

@Dao
interface CollectionDao {

    // Collections
    @Insert
    suspend fun insertCollection(collection: RecipeCollection): Long

    @Query("SELECT * FROM recipe_collections ORDER BY createdAt DESC")
    fun getAllCollections(): LiveData<List<RecipeCollection>>

    @Delete
    suspend fun deleteCollection(collection: RecipeCollection)

    // Collection Items
    @Insert
    suspend fun addRecipeToCollection(item: RecipeCollectionItem)

    @Query("SELECT * FROM recipe_collection_items WHERE collectionId = :collectionId")
    fun getRecipesInCollection(collectionId: Int): LiveData<List<RecipeCollectionItem>>

    @Query("DELETE FROM recipe_collection_items WHERE collectionId = :collectionId AND recipeId = :recipeId")
    suspend fun removeRecipeFromCollection(collectionId: Int, recipeId: Int)
}