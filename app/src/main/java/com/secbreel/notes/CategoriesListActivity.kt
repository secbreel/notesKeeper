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
        val categoriesListFragment = CategoriesListFragment()
        val settingsFragment = SettingsFragment()

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, categoriesListFragment, "CATEGORIES_LIST").commit()

        findViewById<BottomNavigationView>(R.id.bottomNavigation).setOnNavigationItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.navigation_categories -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, categoriesListFragment,"CATEGORIES_LIST").commit()
                    true
                }
                R.id.navigation_settings -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, settingsFragment, "SETTINGS").commit()
                    true
                }
                else -> false
            }

        }
    }
}
