package com.recetas.app.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.RatingBar
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.recetas.app.adapters.RatingAdapter
import com.recetas.app.ui.add.EditRecipeActivity
import com.recetas.app.ui.shopping.ShoppingListActivity
import com.recetas.app.adapters.RecipeDetailPagerAdapter
import com.recetas.app.data.model.Recipe
import com.recetas.app.databinding.ActivityDetailBinding
import com.recetas.app.ui.home.RecipeViewModel
import com.recetas.app.data.model.Rating
import com.recetas.app.data.repository.TagRepository
import com.recetas.app.data.local.database.AppDatabase
import androidx.lifecycle.lifecycleScope
import com.recetas.app.ui.components.adapters.TagAdapter
import com.recetas.app.ui.media.RecipeMediaActivity
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    private lateinit var ratingViewModel: RatingViewModel
    private lateinit var binding: ActivityDetailBinding
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var ratingAdapter: RatingAdapter
    private var recipeId: Int = 0
    private var currentRecipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeId = intent.getIntExtra("RECIPE_ID", 0)

        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        ratingViewModel = ViewModelProvider(this)[RatingViewModel::class.java]

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        binding.toolbar.setNavigationOnClickListener { finish() }

        // Primero el RecyclerView
        setupRatingsRecyclerView()

        // Luego cargamos ratings
        loadRatings()

        // Cargar receta
        loadRecipeData()
        loadRecipeTags()

        binding.favoriteButton.setOnClickListener { toggleFavorite() }

        binding.editButton.setOnClickListener {
            val intent = Intent(this, EditRecipeActivity::class.java)
            intent.putExtra("RECIPE_ID", recipeId)
            startActivity(intent)
        }


        binding.shoppingListButton.setOnClickListener {
            val intent = Intent(this, ShoppingListActivity::class.java)
            intent.putExtra("RECIPE_ID", recipeId)
            startActivity(intent)
        }

        binding.rateButton.setOnClickListener {
            showRatingDialog()
        }
        binding.manageTagsButton.setOnClickListener {
            showManageTagsDialog()
        }
        binding.reminderButton.setOnClickListener {
            showCreateReminderDialog()
        }
        binding.galleryButton.setOnClickListener {
            val intent = Intent(this, RecipeMediaActivity::class.java)
            intent.putExtra("RECIPE_ID", recipeId)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadRecipeData()
    }

    private fun loadRecipeData() {
        recipeViewModel.getRecipeById(recipeId).observe(this) { recipe ->
            if (recipe != null) {
                currentRecipe = recipe

                binding.recipeEmojiDetail.text = recipe.imageUrl ?: "🍽️"
                binding.recipeNameDetail.text = recipe.name
                binding.recipeCategoryDetail.text = recipe.category
                binding.recipeTimeDetail.text = recipe.time
                binding.recipeServingsDetail.text = recipe.servings.toString()
                binding.recipeDifficultyDetail.text = recipe.difficulty

                updateFavoriteIcon(recipe.isFavorite)

                setupViewPager(recipe.ingredients, recipe.instructions)
            }
        }
    }

    // -------- SISTEMA DE RATINGS CORREGIDO -----------
    private fun setupRatingsRecyclerView() {
        ratingAdapter = RatingAdapter()
        binding.ratingsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.ratingsRecyclerView.adapter = ratingAdapter
    }

    private fun loadRatings() {
        ratingViewModel.getRatingsByRecipe(recipeId).observe(this) { ratings ->
            if (ratings.isNotEmpty()) {
                ratingAdapter.setRatings(ratings)

                binding.ratingsCountText.text =
                    "${ratings.size} reseña${if (ratings.size > 1) "s" else ""}"

                val average = ratings.map { it.stars }.average().toFloat()
                binding.averageRatingText.text = String.format("%.1f", average)
                binding.averageRatingBar.rating = average

            } else {
                binding.ratingsCountText.text = "Sin reseñas aún"
                binding.averageRatingText.text = "0.0"
                binding.averageRatingBar.rating = 0f
            }
        }
    }

    private fun showRatingDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Califica esta receta")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val ratingBar = RatingBar(this).apply {
            numStars = 5
            stepSize = 1f
        }

        val commentInput = EditText(this).apply {
            hint = "Escribe tu comentario (opcional)"
            minLines = 3
        }

        layout.addView(ratingBar)
        layout.addView(commentInput)
        builder.setView(layout)

        builder.setPositiveButton("Enviar") { _, _ ->
            val prefs = getSharedPreferences("RecetAppPrefs", MODE_PRIVATE)
            val userName = prefs.getString("name", "Usuario") ?: "Usuario"

            val rating = Rating(
                recipeId = recipeId,
                userName = userName,
                stars = ratingBar.rating.toInt(),
                comment = commentInput.text.toString()
            )

            ratingViewModel.addRating(rating)
            Toast.makeText(this, "¡Valoración enviada!", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }


    // -------- FAVORITOS ----------
    private fun toggleFavorite() {
        currentRecipe?.let { recipe ->
            val updatedRecipe = recipe.copy(isFavorite = !recipe.isFavorite)
            recipeViewModel.update(updatedRecipe)
            currentRecipe = updatedRecipe

            updateFavoriteIcon(updatedRecipe.isFavorite)

            val message = if (updatedRecipe.isFavorite) {
                "Agregado a favoritos ❤️"
            } else {
                "Eliminado de favoritos"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        // Actualiza icono si lo necesitas
    }

    // -------- VIEWPAGER ----------
    private fun setupViewPager(ingredients: String, instructions: String) {
        binding.viewPager.visibility = View.VISIBLE
        binding.tabLayout.visibility = View.VISIBLE

        val ingredientsList = ingredients.split(",").map { it.trim() }

        val adapter = RecipeDetailPagerAdapter(ingredientsList, instructions)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Ingredientes"
                1 -> "Preparación"
                else -> ""
            }
        }.attach()
    }
    private fun showManageTagsDialog() {
        lifecycleScope.launch {
            val database = AppDatabase.getDatabase(this@DetailActivity)
            val tagDao = database.tagDao()
            val tagRepository = TagRepository(tagDao)

            // Obtener todos los tags
            tagRepository.getAllTags().observe(this@DetailActivity) { allTags ->
                if (allTags.isEmpty()) {
                    Toast.makeText(
                        this@DetailActivity,
                        "No hay tags. Crea algunos primero en tu perfil.",
                        Toast.LENGTH_LONG
                    ).show()
                    return@observe
                }

                lifecycleScope.launch {
                    // Verificar cuáles tags ya tiene esta receta
                    val checkedItems = BooleanArray(allTags.size) { index ->
                        tagRepository.isRecipeTagged(recipeId, allTags[index].id)
                    }

                    val tagNames = allTags.map { it.name }.toTypedArray()

                    AlertDialog.Builder(this@DetailActivity)
                        .setTitle("Tags de ${currentRecipe?.name}")

                        .setMultiChoiceItems(tagNames, checkedItems) { _, which, isChecked ->
                            val tag = allTags[which]
                            lifecycleScope.launch {
                                if (isChecked) {
                                    tagRepository.addTagToRecipe(recipeId, tag.id)
                                    Toast.makeText(
                                        this@DetailActivity,
                                        "Tag '${tag.name}' agregado ✅",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    tagRepository.removeTagFromRecipe(recipeId, tag.id)
                                    Toast.makeText(
                                        this@DetailActivity,
                                        "Tag '${tag.name}' eliminado ❌",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                // Recargar tags visibles
                                loadRecipeTags()
                            }
                        }
                        .setPositiveButton("Listo", null)
                        .show()
                }
            }
        }
    }
    private fun loadRecipeTags() {
        lifecycleScope.launch {
            val database = AppDatabase.getDatabase(this@DetailActivity)
            val tagDao = database.tagDao()
            val tagRepository = TagRepository(tagDao)

            tagRepository.getTagsForRecipe(recipeId).observe(this@DetailActivity) { tags ->
                if (tags.isNotEmpty()) {
                    val tagAdapter = TagAdapter(
                        onItemClick = { tag ->
                            Toast.makeText(this@DetailActivity, tag.name, Toast.LENGTH_SHORT).show()
                        },
                        onDeleteClick = null,
                        showDeleteButton = false
                    )

                    binding.recipeTagsRecyclerView.layoutManager =
                        LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
                    binding.recipeTagsRecyclerView.adapter = tagAdapter
                    tagAdapter.setTags(tags)
                    binding.recipeTagsRecyclerView.visibility = View.VISIBLE
                } else {
                    binding.recipeTagsRecyclerView.visibility = View.GONE
                }
            }
        }
    }
    private fun showCreateReminderDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Recordatorio para ${currentRecipe?.name}")

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val messageInput = EditText(this).apply {
            hint = "Mensaje del recordatorio"
            setText("Recuerda preparar ${currentRecipe?.name}")
        }

        val dateTimeText = android.widget.TextView(this).apply {
            text = "Seleccionar fecha y hora"
            textSize = 14f
            setTextColor(getColor(com.recetas.app.R.color.orange_primary))
            setPadding(0, 30, 0, 10)
        }

        val selectedDateTime = java.util.Calendar.getInstance()
        selectedDateTime.add(java.util.Calendar.HOUR, 1)

        dateTimeText.setOnClickListener {
            val datePickerDialog = android.app.DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    selectedDateTime.set(java.util.Calendar.YEAR, year)
                    selectedDateTime.set(java.util.Calendar.MONTH, month)
                    selectedDateTime.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)

                    val timePickerDialog = android.app.TimePickerDialog(
                        this,
                        { _, hourOfDay, minute ->
                            selectedDateTime.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay)
                            selectedDateTime.set(java.util.Calendar.MINUTE, minute)

                            val dateFormat = java.text.SimpleDateFormat("MMM dd, yyyy - HH:mm", java.util.Locale.getDefault())
                            dateTimeText.text = "📅 ${dateFormat.format(selectedDateTime.time)}"
                        },
                        selectedDateTime.get(java.util.Calendar.HOUR_OF_DAY),
                        selectedDateTime.get(java.util.Calendar.MINUTE),
                        true
                    )
                    timePickerDialog.show()
                },
                selectedDateTime.get(java.util.Calendar.YEAR),
                selectedDateTime.get(java.util.Calendar.MONTH),
                selectedDateTime.get(java.util.Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        val dateFormat = java.text.SimpleDateFormat("MMM dd, yyyy - HH:mm", java.util.Locale.getDefault())
        dateTimeText.text = "📅 ${dateFormat.format(selectedDateTime.time)}"

        layout.addView(messageInput)
        layout.addView(dateTimeText)
        builder.setView(layout)

        builder.setPositiveButton("Crear") { _, _ ->
            val message = messageInput.text.toString().trim()

            if (message.isNotEmpty()) {
                lifecycleScope.launch {
                    val database = AppDatabase.getDatabase(this@DetailActivity)
                    val notificationDao = database.notificationDao()

                    val notification = com.recetas.app.data.model.Notification(
                        title = "Recordatorio: ${currentRecipe?.name}",
                        message = message,
                        triggerTime = selectedDateTime.time,
                        type = "RECIPE_REMINDER",
                        isRead = false,
                        createdAt = java.util.Date()
                    )

                    notificationDao.insert(notification)
                    Toast.makeText(this@DetailActivity, "Recordatorio creado ✅", Toast.LENGTH_SHORT).show()
                }
            }
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

}
