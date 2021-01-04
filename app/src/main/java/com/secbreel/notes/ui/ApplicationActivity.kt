package com.secbreel.notes.ui

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.secbreel.notes.R
import java.util.jar.Manifest


class ApplicationActivity : AppCompatActivity() {

    val READ_STORAGE_REQUEST_CODE = 500
    val WRITE_STORAGE_REQUEST_CODE = 501

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        val categoriesListFragment = CategoriesListFragment()
        val settingsFragment = SettingsFragment()

        getPermissions()

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

    fun getPermissions() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READ_STORAGE_REQUEST_CODE)
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_STORAGE_REQUEST_CODE)
    }
}
