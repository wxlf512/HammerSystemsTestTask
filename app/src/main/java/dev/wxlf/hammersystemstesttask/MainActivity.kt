package dev.wxlf.hammersystemstesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        loadFragment(MenuFragment())
        val nav = findViewById<BottomNavigationView>(R.id.bottom_menu)
        nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_menu_item -> loadFragment(MenuFragment())
                R.id.profile_menu_item -> loadFragment(ProfileFragment())
                R.id.basket_menu_item -> loadFragment(BasketFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}