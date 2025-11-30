package com.recetas.app.ui.media

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.recetas.app.R
import com.recetas.app.data.model.RecipeMedia
import com.recetas.app.ui.components.adapters.RecipeMediaAdapter
import kotlinx.coroutines.launch

class RecipeMediaActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeMediaViewModel
    private lateinit var adapter: RecipeMediaAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateLayout: View
    private lateinit var fabAddMedia: FloatingActionButton
    private lateinit var backButton: ImageButton
    private var recipeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_media)

        recipeId = intent.getIntExtra("RECIPE_ID", 0)
        if (recipeId == 0) {
            Toast.makeText(this, "Error: Receta no encontrada", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[RecipeMediaViewModel::class.java]

        initViews()
        setupRecyclerView()
        observeMedia()
        setupListeners()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.mediaRecyclerView)
        emptyStateLayout = findViewById(R.id.emptyStateLayout)
        fabAddMedia = findViewById(R.id.fabAddMedia)
        backButton = findViewById(R.id.backButton)
    }

    private fun setupListeners() {
        fabAddMedia.setOnClickListener {
            showAddMediaDialog()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = RecipeMediaAdapter(
            onItemClick = { media ->
                showImageDialog(media.url)
            },
            onDeleteClick = { media ->
                showDeleteDialog(media)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun showImageDialog(url: String) {
        val builder = AlertDialog.Builder(this)
        val imageView = ImageView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            adjustViewBounds = true
        }

        Glide.with(this)
            .load(url)
            .into(imageView)

        builder.setView(imageView)
        builder.setPositiveButton("Cerrar", null)
        builder.show()
    }

    private fun observeMedia() {
        viewModel.getMediaByRecipe(recipeId).observe(this) { mediaList ->
            if (mediaList.isEmpty()) {
                emptyStateLayout.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                emptyStateLayout.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.setMedia(mediaList)
            }
        }
    }

    private fun showAddMediaDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregar Foto/Video")

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val urlInput = EditText(this).apply {
            hint = "URL de la imagen (ejemplo: https://ejemplo.com/foto.jpg)"
            inputType = android.text.InputType.TYPE_TEXT_VARIATION_URI
        }

        val typeLabel = android.widget.TextView(this).apply {
            text = "Tipo de media:"
            textSize = 14f
            setPadding(0, 20, 0, 10)
        }

        val types = arrayOf("📸 Foto", "🎥 Video")
        var selectedType = "PHOTO"

        layout.addView(urlInput)
        layout.addView(typeLabel)

        builder.setView(layout)
        builder.setSingleChoiceItems(types, 0) { _, which ->
            selectedType = if (which == 0) "PHOTO" else "VIDEO"
        }

        builder.setPositiveButton("Agregar") { _, _ ->
            val url = urlInput.text.toString().trim()
            if (url.isNotEmpty()) {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    lifecycleScope.launch {
                        val order = viewModel.getMediaCount(recipeId)
                        viewModel.addMedia(recipeId, url, selectedType, order)
                        Toast.makeText(this@RecipeMediaActivity, "Foto agregada ✅", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "La URL debe empezar con http:// o https://", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Ingresa una URL válida", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun showDeleteDialog(media: RecipeMedia) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Media")
            .setMessage("¿Eliminar '${media.url}'?")
            .setPositiveButton("Eliminar") { _, _ ->
                viewModel.deleteMedia(media)
                Toast.makeText(this, "Media eliminada", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}