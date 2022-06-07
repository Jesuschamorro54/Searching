package com.example.searching

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login_link = this.findViewById<TextView>(R.id.login_link)
        val create_acount = this.findViewById<TextView>(R.id.createAcount_link)

        login_link.setOnClickListener {
            val loginView = Intent(this, Login::class.java)
            startActivity(loginView)
        }

        create_acount.setOnClickListener {
            val sing_upView = Intent(this, SingUp::class.java)
            startActivity(sing_upView)
        }
    }
}
