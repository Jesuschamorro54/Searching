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

    private var user = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_view)

        intent.getStringExtra("user")?.let {user = it}

        createDefaultFragment()


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val reportView = ReportFragment()
        val generalChat = GeneralChatFragment()
        val mapView = MapViewFragment()
        val home = HomeFragment()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    // TODO EN ESTA SECCIÓN AGREGAN EL CODIGO PARA PASAR LOS PARAMETROS
                    // here
                    //TODO: FIN

                    setCurrentFragment(home)  // Aquí se carga la vista que se quiere
                    true
                }
                R.id.nav_add -> {
                    // TODO EN ESTA SECCIÓN AGREGAN EL CODIGO PARA PASAR LOS PARAMETROS
                    // here
                    //TODO: FIN

                    setCurrentFragment(reportView)
                    true
                }
                R.id.nav_messages -> {
                    // TODO: EN ESTA SECCIÓN AGREGAN EL CODIGO PARA PASAR LOS PARAMETROS
                    generalChat.arguments?.putString("user", user)
                    println(user)

                    // TODO: FIN

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
