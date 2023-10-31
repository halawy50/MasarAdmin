package com.masar.masaradmin.UI

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.masar.masaradmin.R
import com.masar.masaradmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Fragment (HomeFragment) Begin
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val currentDestination = navController.currentDestination?.id
        if (currentDestination == R.id.homePageFragment || currentDestination == R.id.login_Delivery_Fragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}