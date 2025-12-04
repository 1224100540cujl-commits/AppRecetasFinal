package com.recetas.app.ui.collections

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.recetas.app.adapters.RecipeAdapter
import com.recetas.app.data.model.Recipe
import com.recetas.app.databinding.ActivityCollectionDetailBinding
import com.recetas.app.ui.detail.DetailActivity
import com.recetas.app.ui.home.RecipeViewModel
import kotlinx.coroutines.launch

class CollectionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollectionDetailBinding
    private lateinit var collectionViewModel: CollectionViewModel
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var adapter: RecipeAdapter
    private var collectionId: Int = 0
    private var collectionName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener datos del intent
        collectionId = intent.getIntExtra("COLLECTION_ID", 0)
        collectionName = intent.getStringExtra("COLLECTION_NAME") ?: "Colección"

        if (collectionId == 0) {
            Toast.makeText(this, "Error: Colección no encontrada", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // ViewModels
        collectionViewModel = ViewModelProvider(this)[CollectionViewModel::class.java]
        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        // Setup
        binding.collectionTitle.text = collectionName
        setupRecyclerView()
        loadRecipesInCollection()

        // Listeners
        binding.backButton.setOnClickListener { finish() }

        binding.fabAddRecipeToCollection.setOnClickListener {
            showAddRecipeDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = RecipeAdapter { recipe ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("RECIPE_ID", recipe.id)
            startActivity(intent)
        }

        binding.collectionRecipesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.collectionRecipesRecyclerView.adapter = adapter
    }

    private fun loadRecipesInCollection() {
        collectionViewModel.getRecipesInCollection(collectionId).observe(this) { items ->
            if (items.isEmpty()) {
                binding.emptyStateLayout.visibility = View.VISIBLE
                binding.collectionRecipesRecyclerView.visibility = View.GONE
                binding.recipeCountText.text = "0 recetas"
            } else {
                binding.emptyStateLayout.visibility = View.GONE
                binding.collectionRecipesRecyclerView.visibility = View.VISIBLE
                binding.recipeCountText.text = "${items.size} receta${if (items.size > 1) "s" else ""}"

                // Obtener las recetas completas
                lifecycleScope.launch {
                    val recipeIds = items.map { it.recipeId }
                    val recipes = mutableListOf<Recipe>()

                    recipeIds.forEach { recipeId ->
                        recipeViewModel.getRecipeById(recipeId).observe(this@CollectionDetailActivity) { recipe ->
                            if (recipe != null && !recipes.any { it.id == recipe.id }) {
                                recipes.add(recipe)
                                adapter.setRecipes(recipes.sortedBy { it.name })
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showAddRecipeDialog() {
        recipeViewModel.allRecipes.observe(this) { allRecipes ->
            if (allRecipes.isEmpty()) {
                Toast.makeText(this, "No tienes recetas creadas", Toast.LENGTH_SHORT).show()
                return@observe
            }

            // Obtener IDs de recetas ya en la colección
            collectionViewModel.getRecipesInCollection(collectionId).observe(this) { items ->
                val recipeIdsInCollection = items.map { it.recipeId }

                // Filtrar recetas que NO están en la colección
                val availableRecipes = allRecipes.filter { it.id !in recipeIdsInCollection }

                if (availableRecipes.isEmpty()) {
                    Toast.makeText(this, "Todas tus recetas ya están en esta colección", Toast.LENGTH_LONG).show()
                    return@observe
                }

                val recipeNames = availableRecipes.map { "${it.imageUrl ?: "🍽️"} ${it.name}" }.toTypedArray()

                AlertDialog.Builder(this)
                    .setTitle("Agregar Receta a '$collectionName'")
                    .setItems(recipeNames) { _, which ->
                        val selectedRecipe = availableRecipes[which]
                        collectionViewModel.addRecipeToCollection(collectionId, selectedRecipe.id)
                        Toast.makeText(this, "Receta agregada ✅", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
        }
    }
}