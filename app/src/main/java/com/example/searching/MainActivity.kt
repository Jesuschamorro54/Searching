package com.example.searching

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*


class MainActivity : AppCompatActivity() {
    private val GOOGL_SIGN_IN= 100
    private val callbackManager = CallbackManager.Factory.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login_link = this.findViewById<TextView>(R.id.login_link)
        val create_acount = this.findViewById<TextView>(R.id.createAcount_link)
        val google_acount = this.findViewById<TextView>(R.id.googleButton)
        val facebook_acount =this.findViewById<TextView>(R.id.facebookbuttom)

        login_link.setOnClickListener {
            val loginView = Intent(this, Login::class.java)
            startActivity(loginView)
        }

        create_acount.setOnClickListener {
            val sing_upView = Intent(this, SingUp::class.java)
            startActivity(sing_upView)
        }
        google_acount.setOnClickListener{
            sign_google()
        }
        facebook_acount.setOnClickListener{
            sign_google()
        }



    }
    private fun sign_google(){
        googleButton.setOnClickListener {
            val token_id="675710628734-ajp6bnfhhca6rruei19gh93dhdd3m5j6.apps.googleusercontent.com"
            val googleConf:GoogleSignInOptions=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(token_id).requestEmail().build()
            val googleClient =GoogleSignIn.getClient(this,googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent,GOOGL_SIGN_IN)

        }
        facebookbuttom.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this,listOf("email"))
            LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult?) {
                   result?.let {
                       val token= it.accessToken

                       val credential : AuthCredential = FacebookAuthProvider.getCredential(token.token)
                       FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                           if(it.isSuccessful){
                               showHome(it.result?.user?.email ?: "", ProviderType.FACEBOOK)
                           }else{
                               showAlert("Se ha producido un error autenticando al usuario")
                           }
                       }
                   }
                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {
                    showAlert("Se ha producido un error autenticando al usuario")
                }
            })
        }
    }

    private fun showAlert(mensaje:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email:String,provider: ProviderType){
        val homeIntent = Intent(this, MenuView::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==GOOGL_SIGN_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account :GoogleSignInAccount = task.getResult(ApiException::class.java)
                if (account!=null){
                    val credential : AuthCredential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){
                            showHome(account.email ?: "", ProviderType.GOOGLE)
                        }else{
                            showAlert("Se ha producido un error autenticando al usuario")
                        }
                    }
                }
            }catch (e: ApiException){
                showAlert("Se ha producido un error autenticando al usuario")
            }


        }
    }
}
