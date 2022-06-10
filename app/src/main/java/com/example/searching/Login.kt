package com.example.searching
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.sing_up.*

class Login: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)




       login_()
    }
     private fun login_(){
        button_login.setOnClickListener {
            if (user.text.isNotEmpty() &&  password.text.isNotEmpty() ){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(user.text.toString(),password.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert("Se ha producido un error autenticando al usuario (NO EXISTE)")
                        }
                    }

            }else{
                showAlert("hay campos vacios")
            }

        }
         forgotPassword.setOnClickListener{
             val passwordView = Intent(this, PasswordReset::class.java)
             startActivity(passwordView)
         }
    }

    private fun showAlert(mensaje:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("aceptar", null)
        val dialog:AlertDialog= builder.create()
        dialog.show()
    }

    private fun showHome(email:String,provider: ProviderType){
        val homeIntent = Intent(this, MenuView::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}
