package com.recetas.app.ui.tags

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.recetas.app.data.local.database.AppDatabase
import com.recetas.app.data.model.Tag
import com.recetas.app.data.repository.TagRepository
import kotlinx.coroutines.launch

class TagViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TagRepository
    val allTags: LiveData<List<Tag>>

    init {
        val tagDao = AppDatabase.getDatabase(application).tagDao()
        repository = TagRepository(tagDao)
        allTags = repository.getAllTags()
    }

    fun getTagsForRecipe(recipeId: Int): LiveData<List<Tag>> {
        return repository.getTagsForRecipe(recipeId)
    }

    fun addTag(name: String, color: String) = viewModelScope.launch {
        val tag = Tag(name = name, color = color)
        repository.insertTag(tag)
    }

    fun updateTag(tag: Tag) = viewModelScope.launch {
        repository.updateTag(tag)
    }

    fun deleteTag(tag: Tag) = viewModelScope.launch {
        repository.deleteTag(tag)
    }

    fun addTagToRecipe(recipeId: Int, tagId: Int) = viewModelScope.launch {
        repository.addTagToRecipe(recipeId, tagId)
    }

    fun removeTagFromRecipe(recipeId: Int, tagId: Int) = viewModelScope.launch {
        repository.removeTagFromRecipe(recipeId, tagId)
    }

    suspend fun isRecipeTagged(recipeId: Int, tagId: Int): Boolean {
        return repository.isRecipeTagged(recipeId, tagId)
    }

    suspend fun getRecipeCountForTag(tagId: Int): Int {
        return repository.getRecipeCountForTag(tagId)
    }
}