package com.example.searching

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_general_chat.*
enum class ProviderType{
    BASIC,
    GOOGLE,
    FACEBOOK
}

class MenuView : AppCompatActivity(), OnMapReadyCallback  {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_view)
        createDefaultFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val reportView = ReportFragment()
        val generalChat = GeneralChatFragment()
        val mapView = MapViewFragment()
        val home = HomeFragment()



        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    setCurrentFragment(home)  // Aquí se carga la vista que se quiere
                    true
                }
                R.id.nav_add -> {
                    setCurrentFragment(reportView)
                    true
                }
                R.id.nav_messages -> {
                    setCurrentFragment(generalChat)
                    true
                }
                R.id.nav_map -> {
                    setCurrentFragment(mapView)
                    true
                }
                else -> false
            }
        }
        val bundle=intent.extras
        val email = bundle?.getString("email")
        val provider=bundle?.getString("provider")
        //Guardado de datos (google)
        val prefs: SharedPreferences.Editor? =getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs?.putString("email", email)
        prefs?.putString("procider",provider)
        prefs?.apply ()
    }

    private fun createDefaultFragment() {
        val home = HomeFragment()
        setCurrentFragment(home)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView, fragment)
            commit()
        }
    }

    private fun changedView (view: Intent){
        startActivity(view)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }
}