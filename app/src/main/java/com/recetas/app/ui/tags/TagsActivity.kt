package com.recetas.app.ui.tags

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.recetas.app.R
import com.recetas.app.data.model.Tag
import com.recetas.app.ui.components.adapters.TagAdapter
import kotlinx.coroutines.launch

class TagsActivity : AppCompatActivity() {

    private lateinit var viewModel: TagViewModel
    private lateinit var adapter: TagAdapter
    private lateinit var tagsRecyclerView: RecyclerView
    private lateinit var emptyStateLayout: View
    private lateinit var fabAddTag: FloatingActionButton
    private lateinit var backButton: ImageButton

    private val predefinedColors = listOf(
        "#FF5722", "#E91E63", "#9C27B0", "#673AB7",
        "#3F51B5", "#2196F3", "#03A9F4", "#00BCD4",
        "#009688", "#4CAF50", "#8BC34A", "#CDDC39",
        "#FFC107", "#FF9800", "#FF5722", "#795548"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tags)

        viewModel = ViewModelProvider(this)[TagViewModel::class.java]

        initViews()
        setupRecyclerView()
        observeTags()
        setupListeners()
    }

    private fun initViews() {
        tagsRecyclerView = findViewById(R.id.tagsRecyclerView)
        emptyStateLayout = findViewById(R.id.emptyStateLayout)
        fabAddTag = findViewById(R.id.fabAddTag)
        backButton = findViewById(R.id.backButton)
    }

    private fun setupListeners() {
        fabAddTag.setOnClickListener {
            showCreateTagDialog()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = TagAdapter(
            onItemClick = { tag ->
                Toast.makeText(this, "Ver recetas con tag: ${tag.name}", Toast.LENGTH_SHORT).show()
            },
            onDeleteClick = { tag ->
                showDeleteDialog(tag)
            },
            showDeleteButton = true
        )

        tagsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        tagsRecyclerView.adapter = adapter
    }

    private fun observeTags() {
        viewModel.allTags.observe(this) { tags ->
            if (tags.isEmpty()) {
                emptyStateLayout.visibility = View.VISIBLE
                tagsRecyclerView.visibility = View.GONE
            } else {
                emptyStateLayout.visibility = View.GONE
                tagsRecyclerView.visibility = View.VISIBLE
                adapter.setTags(tags)

                loadRecipeCounts(tags)
            }
        }
    }

    private fun loadRecipeCounts(tags: List<Tag>) {
        lifecycleScope.launch {
            val counts = mutableMapOf<Int, Int>()
            tags.forEach { tag ->
                val count = viewModel.getRecipeCountForTag(tag.id)
                counts[tag.id] = count
            }
            adapter.setRecipeCounts(counts)
        }
    }

    private fun showCreateTagDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nuevo Tag")

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val nameInput = EditText(this).apply {
            hint = "Nombre del tag (ej: Vegetariano)"
        }

        layout.addView(nameInput)
        builder.setView(layout)

        builder.setPositiveButton("Crear") { _, _ ->
            val name = nameInput.text.toString().trim()
            if (name.isNotEmpty()) {
                val randomColor = predefinedColors.random()
                viewModel.addTag(name, randomColor)
                Toast.makeText(this, "Tag creado ✅", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Escribe un nombre", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun showDeleteDialog(tag: Tag) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Tag")
            .setMessage("¿Eliminar '${tag.name}'?")
            .setPositiveButton("Eliminar") { _, _ ->
                viewModel.deleteTag(tag)
                Toast.makeText(this, "Tag eliminado", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}