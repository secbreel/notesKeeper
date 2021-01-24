package com.secbreel.notes.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.secbreel.notes.R
import kotlinx.android.synthetic.main.activity_application.*


class ApplicationActivity : AppCompatActivity() {

    val READ_STORAGE_REQUEST_CODE = 500
    val WRITE_STORAGE_REQUEST_CODE = 501
    lateinit var navigationController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
        setUpToolBar()
        navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setSupportActionBar(findViewById(R.id.mainToolBar))

        getPermissions()
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_categories, R.id.navigation_calendar, R.id.navigation_settings))
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.mainToolBar).setupWithNavController(navigationController, appBarConfiguration)
        findViewById<BottomNavigationView>(R.id.bottomNavigation).setupWithNavController(navigationController)
    }

    private fun getPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_STORAGE_REQUEST_CODE
            )
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                WRITE_STORAGE_REQUEST_CODE
            )
    }

    private fun setUpToolBar() {
        setSupportActionBar(findViewById(R.id.mainToolBar))
    }


}

