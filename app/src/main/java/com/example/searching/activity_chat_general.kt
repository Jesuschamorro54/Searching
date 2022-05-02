package com.example.searching


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButtonToggleGroup

class activity_chat_general : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_general)
        val personal = findViewById<LinearLayout>(R.id.chat_personal)

        personal.setOnClickListener {
            val personalView = Intent(this, activity_chat_personal::class.java)
            startActivity(personalView)
        }

    }
}