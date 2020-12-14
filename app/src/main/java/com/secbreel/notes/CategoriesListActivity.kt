package com.secbreel.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView


class CategoriesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_list)
        val categoriesGrid = findViewById<GridView>(R.id.categoriesGrid)
        val categories = listOf(
                Category("Recepies", 1),
                Category("Books", 3),
                Category("Airsoft", 2)
        )
        categoriesGrid.adapter = CategoriesAdapter(categories) { view, category ->
            view.findViewById<TextView>(R.id.categoryTitle).text = category.title
            view.findViewById<TextView>(R.id.notesCount).text = "${category.notesCount}"
        }

        findViewById<Button>(R.id.addCategoryButton).setOnClickListener {
            /*Toast.makeText(
                    this,
                    "clicked!",
                    Toast.LENGTH_SHORT
            ).show()*/
            startActivity(Intent(this, CreateCategoryActivity::class.java))
        }

        findViewById<BottomNavigationView>(R.id.bottomNavigation).setOnNavigationItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.navigation_categories -> {
                    true
                }
                R.id.navigation_settings -> {
                    true
                }
                else -> false
            }

        }
    }
}
