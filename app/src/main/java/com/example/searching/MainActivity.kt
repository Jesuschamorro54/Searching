package com.example.searching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton=findViewById<Button>(R.id.boton_iniciar)
        boton.setOnClickListener{
            val mainactivityperdidos=Intent(this, MainActivityPerdidos::class.java)
            startActivity(mainactivityperdidos)
        }

    }
}