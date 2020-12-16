package com.secbreel.notes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.secbreel.notes.R


class ApplicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
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
