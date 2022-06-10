package com.example.searching


import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.sing_up.*


class SingUp: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sing_up)

        val to_access = this.findViewById<TextView>(R.id.button_register)

        to_access.setOnClickListener {
            val homeView = Intent(this, MenuView::class.java)
            startActivity(homeView)
        }
        setup()
    }

    private fun setup(){
       button_register.setOnClickListener {
            if (nameSingUp.text.isNotEmpty() && email_sing.text.isNotEmpty()&& password_sing.text.isNotEmpty() && password_sing_confirm.text.isNotEmpty()){
                if (password_sing.text.toString() == password_sing_confirm.text.toString()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        email_sing.text.toString(),password_sing.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert("Se ha producido un error autenticando al usuario")
                        }
                    }
                }else{
                    showAlert("las contraseñas no son iguales")
                }
            }else{
                showAlert("hay campos vacios")
            }
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