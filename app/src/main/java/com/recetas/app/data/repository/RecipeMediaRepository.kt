package com.recetas.app.data.repository

import androidx.lifecycle.LiveData
import com.recetas.app.data.local.dao.RecipeMediaDao
import com.recetas.app.data.model.RecipeMedia
import java.util.Date

class RecipeMediaRepository(private val mediaDao: RecipeMediaDao) {

    fun getMediaByRecipe(recipeId: Int): LiveData<List<RecipeMedia>> {
        return mediaDao.getMediaByRecipe(recipeId)
    }

    suspend fun insert(media: RecipeMedia): Long {
        return mediaDao.insert(media)
    }

    suspend fun addMedia(recipeId: Int, url: String, type: String, order: Int): Long {
        val media = RecipeMedia(
            recipeId = recipeId,
            url = url,
            type = type,
            order = order,
            createdAt = System.currentTimeMillis()

        )
        return mediaDao.insert(media)
    }

    suspend fun update(media: RecipeMedia) {
        mediaDao.update(media)
    }

    suspend fun delete(media: RecipeMedia) {
        mediaDao.delete(media)
    }

    suspend fun deleteAllByRecipe(recipeId: Int) {
        mediaDao.deleteAllByRecipe(recipeId)
    }

    suspend fun getMediaCount(recipeId: Int): Int {
        return mediaDao.getMediaCount(recipeId)
    }

    suspend fun getMediaById(id: Int): RecipeMedia? {
        return mediaDao.getMediaById(id)
    }
}