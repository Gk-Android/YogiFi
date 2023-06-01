package com.example.yogifi.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.yogifi.R
import com.example.yogifi.data.repository.ProductRepository
import com.example.yogifi.data.webservice.RetrofitClient
import com.example.yogifi.data.webservice.Webservice
import com.example.yogifi.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    lateinit var webservice:Webservice
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController=navHostFragment.navController

        setupWithNavController(binding.bottomNavBar , navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.productItemDetail) {
                binding.bottomNavBar.visibility = View.GONE
            } else {
                binding.bottomNavBar.visibility = View.VISIBLE
            }
        }
    }
}