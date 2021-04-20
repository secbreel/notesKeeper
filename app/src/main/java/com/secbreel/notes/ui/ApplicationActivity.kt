package com.secbreel.notes.ui

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.secbreel.notes.R
import com.secbreel.notes.databinding.ActivityApplicationBinding
import com.secbreel.notes.ui.calendar.CalendarFragment
import com.secbreel.notes.ui.categories_list.CategoriesListFragment
import com.secbreel.notes.ui.settings.SettingsFragment
import org.koin.android.ext.android.inject


class ApplicationActivity : AppCompatActivity(R.layout.activity_application) {
    //TODO set up toolbar with back arrow
    //TODO убрать из метода onDestroy deInit *использовать kotlin extension и LifeCycle observer*
    //TODO привязать поведение bottomNavigation к навигации *viewPager2 FragmentStateAdapter NavigationFragment(написать самому)* либо привязать простую навигацию на фрагменты(при нажатии на bottomNavigation перевести на нужный фрагмент)
    val READ_STORAGE_REQUEST_CODE = 500
    val WRITE_STORAGE_REQUEST_CODE = 501
    private val viewBinding by viewBinding(ActivityApplicationBinding::bind)
    private val navigationHolder by inject<NavigatorHolder>()
    private val router by inject<Router>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
        AppCompatDelegate.setDefaultNightMode(
            getSharedPreferences("preferences", MODE_PRIVATE).getInt(
                "theme",
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        )

        setUpToolBar()

        viewBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_categories -> {
                    router.replaceScreen(FragmentScreen{CategoriesListFragment()})
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_calendar -> {
                    router.replaceScreen(FragmentScreen{CalendarFragment()})
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_settings-> {
                    router.replaceScreen(FragmentScreen{SettingsFragment()})
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false

        }

        val fragmentManager = supportFragmentManager
        fragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                super.onFragmentResumed(fm, f)
                if(supportFragmentManager.backStackEntryCount > 0)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                else supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }

        }, true)

        getPermissions()
    }

    override fun onDestroy() {
        super.onDestroy()
        deInitNavigation()
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
        viewBinding.toolBar.mainToolBar.setNavigationOnClickListener { router.exit() }
    }

    private fun initNavigation() {
        val navigator = AppNavigator(this, R.id.nav_host_fragment_container)
        navigationHolder.setNavigator(navigator)
        router.replaceScreen(FragmentScreen {
            CategoriesListFragment()
        })
    }

    private fun deInitNavigation() {
        navigationHolder.removeNavigator()
    }
}


