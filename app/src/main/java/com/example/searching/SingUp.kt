package com.example.searching

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SingUp: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sing_up)

        val to_access = this.findViewById<TextView>(R.id.button_register)

        to_access.setOnClickListener {
            val homeView = Intent(this, MenuView::class.java)
            startActivity(homeView)
        }
    }
}