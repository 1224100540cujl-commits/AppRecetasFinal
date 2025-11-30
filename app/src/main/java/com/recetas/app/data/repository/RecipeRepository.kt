package com.recetas.app.data.repository

import androidx.lifecycle.LiveData
import com.recetas.app.data.local.dao.RecipeDao
import com.recetas.app.data.model.Recipe

class RecipeRepository(private val recipeDao: RecipeDao) {

    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()
    val favorites: LiveData<List<Recipe>> = recipeDao.getFavorites()

    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    suspend fun update(recipe: Recipe) {
        recipeDao.update(recipe)
    }

    suspend fun delete(recipe: Recipe) {
        recipeDao.delete(recipe)
    }

    fun searchRecipes(query: String): LiveData<List<Recipe>> {
        return recipeDao.searchRecipes(query)
    }

    fun getRecipesByCategory(category: String): LiveData<List<Recipe>> {
        return recipeDao.getRecipesByCategory(category)
    }

    fun getRecipeById(id: Int): LiveData<Recipe> {
        return recipeDao.getRecipeById(id)
    }
}