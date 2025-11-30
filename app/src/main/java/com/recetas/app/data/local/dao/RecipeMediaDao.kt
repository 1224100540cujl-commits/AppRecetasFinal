package com.recetas.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.recetas.app.data.model.RecipeMedia

@Dao
interface RecipeMediaDao {

    @Insert
    suspend fun insert(media: RecipeMedia): Long

    @Update
    suspend fun update(media: RecipeMedia)

    @Delete
    suspend fun delete(media: RecipeMedia)

    @Query("SELECT * FROM recipe_media WHERE recipeId = :recipeId ORDER BY `order` ASC")
    fun getMediaByRecipe(recipeId: Int): LiveData<List<RecipeMedia>>

    @Query("DELETE FROM recipe_media WHERE recipeId = :recipeId")
    suspend fun deleteAllByRecipe(recipeId: Int)

    @Query("SELECT COUNT(*) FROM recipe_media WHERE recipeId = :recipeId")
    suspend fun getMediaCount(recipeId: Int): Int

    @Query("SELECT * FROM recipe_media WHERE id = :mediaId")
    suspend fun getMediaById(mediaId: Int): RecipeMedia?
}