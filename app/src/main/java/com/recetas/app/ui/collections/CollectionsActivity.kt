package com.recetas.app.ui.collections

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.recetas.app.databinding.ActivityCollectionsBinding
import com.recetas.app.ui.components.adapters.CollectionAdapter

class CollectionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollectionsBinding
    private lateinit var viewModel: CollectionViewModel
    private lateinit var adapter: CollectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CollectionViewModel::class.java]

        setupRecyclerView()
        observeCollections()

        binding.fabAddCollection.setOnClickListener {
            showCreateCollectionDialog()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = CollectionAdapter(
            onItemClick = { collection ->
                // Abrir CollectionDetailActivity
                Toast.makeText(this, "Colección: ${collection.name}", Toast.LENGTH_SHORT).show()
            },
            onDeleteClick = { collection ->
                viewModel.deleteCollection(collection)
                Toast.makeText(this, "Colección eliminada", Toast.LENGTH_SHORT).show()
            }
        )


        binding.collectionsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.collectionsRecyclerView.adapter = adapter
    }

    private fun observeCollections() {
        viewModel.allCollections.observe(this) { collections ->
            if (collections.isEmpty()) {
                binding.emptyStateLayout.visibility = View.VISIBLE
                binding.collectionsRecyclerView.visibility = View.GONE
            } else {
                binding.emptyStateLayout.visibility = View.GONE
                binding.collectionsRecyclerView.visibility = View.VISIBLE
                adapter.setCollections(collections)
            }
        }
    }

    private fun showCreateCollectionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nueva Colección")

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val nameInput = EditText(this).apply {
            hint = "Nombre de la colección"
        }

        val descInput = EditText(this).apply {
            hint = "Descripción (opcional)"
        }

        layout.addView(nameInput)
        layout.addView(descInput)
        builder.setView(layout)

        builder.setPositiveButton("Crear") { _, _ ->
            val name = nameInput.text.toString().trim()
            if (name.isNotEmpty()) {
                viewModel.createCollection(name, descInput.text.toString())
                Toast.makeText(this, "Colección creada ✅", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }
}