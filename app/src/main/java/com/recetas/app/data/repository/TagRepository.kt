package com.recetas.app.data.repository

import androidx.lifecycle.LiveData
import com.recetas.app.data.local.dao.TagDao
import com.recetas.app.data.model.Tag
import com.recetas.app.data.model.RecipeTag

class TagRepository(private val tagDao: TagDao) {

    fun getAllTags(): LiveData<List<Tag>> {
        return tagDao.getAllTags()
    }

    fun getTagsForRecipe(recipeId: Int): LiveData<List<Tag>> {
        return tagDao.getTagsForRecipe(recipeId)
    }

    suspend fun insertTag(tag: Tag): Long {
        return tagDao.insertTag(tag)
    }

    suspend fun updateTag(tag: Tag) {
        tagDao.updateTag(tag)
    }

    suspend fun deleteTag(tag: Tag) {
        tagDao.deleteTag(tag)
    }

    suspend fun addTagToRecipe(recipeId: Int, tagId: Int) {
        val recipeTag = RecipeTag(recipeId = recipeId, tagId = tagId)
        tagDao.insertRecipeTag(recipeTag)
    }

    suspend fun removeTagFromRecipe(recipeId: Int, tagId: Int) {
        tagDao.removeTagFromRecipe(recipeId, tagId)
    }

    suspend fun isRecipeTagged(recipeId: Int, tagId: Int): Boolean {
        return tagDao.isRecipeTagged(recipeId, tagId) > 0
    }

    suspend fun getRecipeCountForTag(tagId: Int): Int {
        return tagDao.getRecipeCountForTag(tagId)
    }

    suspend fun getTagsCount(): Int {
        return tagDao.getTagsCount()
    }
}