package com.oyoke.iara

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oyoke.iara.databinding.ActivityMainBinding
import com.oyoke.iara.info.InfoFragment
import com.oyoke.iara.util.TimerUtil

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var currentFragmentName: String

    private val infoFragment = InfoFragment()

    private val PREFS_NAME = "MainPrefs"
    private val KEY_FIRST_TIME = "firstTime"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        val navController = navHostFragment.navController

        val firstTime = getFirstTimeStatus()
        if (firstTime) {
            setFirstTimeStatus()
            currentFragmentName = "First Time"
            navigateToInfoFragment(navController)
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        binding.bottomNavigation.selectedItemId = R.id.action_home
        bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.action_home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.action_games -> {
                    navController.navigate(R.id.gameMenuFragment)
                    true
                }

                R.id.action_quote -> {
                    navController.navigate(R.id.quoteFragment)
                    true
                }

                else -> true
            }
        }

        binding.ivInfo.setOnClickListener {
            navigateToInfoFragment(navController)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        changeSelectedBottomNavigationItem()
    }
    fun changeSelectedBottomNavigationItem() {
        when (currentFragmentName) {
            "Home" -> binding.bottomNavigation.selectedItemId = R.id.action_home
            "Games" ->   binding.bottomNavigation.selectedItemId = R.id.action_games
            "Quote" ->   binding.bottomNavigation.selectedItemId = R.id.action_quote
        }
    }

    private fun navigateToInfoFragment(navController: androidx.navigation.NavController) {
        infoFragment.arguments = Bundle().apply {
            putString("currentFragmentName", currentFragmentName)
        }
        navController.navigate(R.id.infoFragment, infoFragment.arguments)

    }

    private fun getFirstTimeStatus(): Boolean {
        val sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_FIRST_TIME, true)
    }

    private fun setFirstTimeStatus() {
        val sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_FIRST_TIME, false)
        editor.apply()
    }

    fun showToolbar() {
        binding.toolbar.visibility = View.VISIBLE
    }

    fun hideToolbar() {
        binding.toolbar.visibility = View.GONE
    }
    fun showCVTimer() {
        binding.cvTimer.visibility = View.VISIBLE
    }
    fun hideCVTimer() {
        binding.cvTimer.visibility = View.GONE
    }
    fun showBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun updateFragment(currentFragmentName: String) {
        binding.tvTitle.text = currentFragmentName
        this.currentFragmentName = currentFragmentName
    }
    fun getTimer():String{
        return binding.tvTimer.text.toString()
    }
    fun updateTimer(totalTime: Long?,typeTimer: String,) {
        showCVTimer()
        TimerUtil.reset()
        TimerUtil.getInstance(
            totalTime,
            binding.tvTimer,
            findNavController(R.id.nav_host_fragment_main),typeTimer
        ).startTimer()
    }

    fun removeTimer(typeTimer: String) {
        hideCVTimer()
        TimerUtil.getInstance( null, binding.tvTimer, findNavController(R.id.nav_host_fragment_main),typeTimer)
            .removeTimer()
    }

    fun decreaseTimer(timeToDecrease: Long) {
        TimerUtil.getInstance(null, binding.tvTimer, findNavController(R.id.nav_host_fragment_main),"countDown")
            .decreaseTimer(timeToDecrease)
    }
}
