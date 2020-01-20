package com.juniar.ancodev.sinauneh.feature

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.juniar.ancodev.sinauneh.R
import com.juniar.ancodev.sinauneh.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.nav_view

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onViewReady(savedInstanceState: Bundle?) {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_post, R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }
}
