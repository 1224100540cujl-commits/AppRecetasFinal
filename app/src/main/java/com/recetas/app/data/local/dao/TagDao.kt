package com.recetas.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.recetas.app.data.model.Tag
import com.recetas.app.data.model.RecipeTag

@Dao
interface TagDao {

    // ========== TAGS ==========

    @Insert
    suspend fun insertTag(tag: Tag): Long

    @Update
    suspend fun updateTag(tag: Tag)

    @Delete
    suspend fun deleteTag(tag: Tag)

    @Query("SELECT * FROM tags ORDER BY name ASC")
    fun getAllTags(): LiveData<List<Tag>>

    @Query("SELECT * FROM tags WHERE id = :tagId")
    suspend fun getTagById(tagId: Int): Tag?

    @Query("SELECT COUNT(*) FROM tags")
    suspend fun getTagsCount(): Int

    // ========== RECIPE TAGS ==========

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeTag(recipeTag: RecipeTag)

    @Delete
    suspend fun deleteRecipeTag(recipeTag: RecipeTag)

    @Query("DELETE FROM recipe_tags WHERE recipeId = :recipeId AND tagId = :tagId")
    suspend fun removeTagFromRecipe(recipeId: Int, tagId: Int)

    @Query("SELECT * FROM tags WHERE id IN (SELECT tagId FROM recipe_tags WHERE recipeId = :recipeId)")
    fun getTagsForRecipe(recipeId: Int): LiveData<List<Tag>>

    @Query("SELECT COUNT(*) FROM recipe_tags WHERE recipeId = :recipeId AND tagId = :tagId")
    suspend fun isRecipeTagged(recipeId: Int, tagId: Int): Int

    @Query("SELECT COUNT(*) FROM recipe_tags WHERE tagId = :tagId")
    suspend fun getRecipeCountForTag(tagId: Int): Int
}