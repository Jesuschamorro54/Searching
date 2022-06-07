package com.example.searching
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val to_access = this.findViewById<Button>(R.id.button_login)

        to_access.setOnClickListener {
            val menuView = Intent(this, MenuView::class.java)

            menuView.putExtra("user", "jesusfeli54@gmail.com")
            startActivity(menuView)
            finish()
        }
    }
}