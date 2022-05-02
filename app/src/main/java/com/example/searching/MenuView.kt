package com.example.searching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_general_chat.*

class MenuView : AppCompatActivity(), OnMapReadyCallback  {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_view)
        createDefaultFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val mapView = MapViewFragment()
        val personalChat = GeneralChatFragment()
        val home = HomeFragment()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    setCurrentFragment(home)  // Aquí se carga la vista que se quiere
                    true
                }
                R.id.nav_add -> {
                    setCurrentFragment(mapView)
                    true
                }
                R.id.nav_messages -> {
                    setCurrentFragment(personalChat)
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView, fragment)
            commit()
        }
    }

    private fun createDefaultFragment() {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }
}