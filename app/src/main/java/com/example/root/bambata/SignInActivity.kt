package com.example.root.bambata

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.nav_header_start.view.*

class SignInActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var gso: GoogleSignInOptions
    val RC_SIGN_IN: Int = 1
    lateinit var signOut: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val newAccountButton = findViewById<TextView>(R.id.create_account)
        newAccountButton.setOnClickListener{
            val StartActivity = Intent(this, NewUserRegistration::class.java )
            startActivity(StartActivity)
        }

        val googleSingnIn = findViewById<View>(R.id.google_sign_in_button) as  SignInButton
        val textView = googleSingnIn.getChildAt(0) as TextView
        textView.text ="Se connecter avec Google"
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signOut = findViewById<View>(R.id.logout) as Button
        signOut.visibility = View.INVISIBLE
        googleSingnIn.setOnClickListener {
            view: View? -> singnInGoogle ()
        }
    }

    private fun singnInGoogle () {
        val signInIntent: Intent =  mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult (task)
        }
    }

    private fun handleResult (completedTask: Task<GoogleSignInAccount>) {
      try {
          val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
          UpdateUI (account)
      } catch (e: ApiException){
          Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
      }
    }

    private fun UpdateUI (account: GoogleSignInAccount) {
        val username = findViewById<View>(R.id.username) as TextView
        username.text = account.displayName
        signOut.visibility = View.VISIBLE
        google_sign_in_button.visibility = View.INVISIBLE
        signOut.setOnClickListener{
            view: View? -> mGoogleSignInClient.signOut().addOnCompleteListener {
            task: Task<Void> ->  username.text = " "
            signOut.visibility = View.INVISIBLE
            google_sign_in_button.visibility = View.VISIBLE

        }
        }
    }

}
