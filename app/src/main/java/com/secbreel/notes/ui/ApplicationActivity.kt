package com.secbreel.notes.ui

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.secbreel.notes.R
import com.secbreel.notes.databinding.ActivityApplicationBinding


class ApplicationActivity : AppCompatActivity() {

    val READ_STORAGE_REQUEST_CODE = 500
    val WRITE_STORAGE_REQUEST_CODE = 501
    lateinit var navigationController: NavController
    private lateinit var viewBinding: ActivityApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        AppCompatDelegate.setDefaultNightMode(
            getSharedPreferences("preferences", MODE_PRIVATE).getInt(
                "theme",
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        )
        setUpToolBar()
        navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)

        getPermissions()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_categories,
                R.id.navigation_calendar,
                R.id.navigation_settings
            )
        )
        viewBinding.toolBar.mainToolBar.setupWithNavController(
            navigationController,
            appBarConfiguration
        )
        viewBinding.bottomNavigation.setupWithNavController(
            navigationController
        )
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
        setSupportActionBar(viewBinding.toolBar.mainToolBar)
    }

}

