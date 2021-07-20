package com.secbreel.notes.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.jakewharton.rxbinding4.appcompat.navigationClicks
import com.secbreel.notes.R
import com.secbreel.notes.databinding.ActivityApplicationBinding
import com.secbreel.notes.ui.ext.*
import com.secbreel.notes.ui.screens.calendar.CalendarFragment
import com.secbreel.notes.ui.screens.categories_list.CategoriesListFragment
import com.secbreel.notes.ui.screens.settings.SettingsFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ApplicationActivity : AppCompatActivity(R.layout.activity_application) {
    //TODO привязать поведение bottomNavigation к навигации *viewPager2 FragmentStateAdapter NavigationFragment(написать самому)* либо привязать простую навигацию на фрагменты(при нажатии на bottomNavigation перевести на нужный фрагмент)

    private val viewBinding by viewBinding(ActivityApplicationBinding::bind)
    private val navigationHolder by inject<NavigatorHolder>()
    private val router by inject<Router>()
    private val viewModel by viewModel<BaseApplicationActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()

        viewModel.setup()

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
                    router.replaceScreen(FragmentScreen { CategoriesListFragment() })
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_calendar -> {
                    router.replaceScreen(FragmentScreen { CalendarFragment() })
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    router.replaceScreen(FragmentScreen { SettingsFragment() })
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false

        }

        getPermissions(this)
    }


    private fun BaseApplicationActivityViewModel.setup() {
        attach(object : BaseApplicationActivityViewModel.Input {
            override val onReturnClicked =
                viewBinding.toolBar.mainToolBar.navigationClicks()
        }).subscribe(this@ApplicationActivity)
    }


    private fun setUpToolBar() = viewBinding.toolBar.run {
        onCurrentFragmentChanged {
            arguments?.let {
                if (supportFragmentManager.backStackEntryCount > 0) mainToolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
                else mainToolBar.navigationIcon = null

                mainToolBar.title = it.toolbarTitle ?: ""
            }
        }
    }

    private fun initNavigation() {
        val navigator = AppNavigator(this, R.id.nav_host_fragment_container)
        navigationHolder.setNavigatorWithLifecycle(navigator, this)
        router.replaceScreen(FragmentScreen {
            CategoriesListFragment().withArguments {
                it.toolbarTitle = "Categories new"
            }
        })
    }



    companion object {
        var Bundle.toolbarTitle: String?
            get() = getString("toolbarTitle")
            set(value) = this.putString("toolbarTitle", value)
    }
}


