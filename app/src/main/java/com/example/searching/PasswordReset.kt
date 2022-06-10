package com.example.searching

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login.*


class PasswordReset : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_reset)

        val username = this.findViewById<TextView>(R.id.code)
        val cambiar_link = this.findViewById<TextView>(R.id.button_cambiarP)
        cambiar_link.setOnClickListener{
            changePassword(username as EditText)
        }


    }

    private fun changePassword(username:EditText){
        if (username.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return
        }


        Firebase.auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Email sent",Toast.LENGTH_SHORT).show()
                }
            }
    }
}