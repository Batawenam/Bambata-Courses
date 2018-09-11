package com.example.root.bambata

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_new_user_registration.*
import java.util.concurrent.TimeUnit

class NewUserRegistration : AppCompatActivity() {

    lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user_registration)

        new_account.setOnClickListener {
            view: View? -> startActivity(Intent(baseContext, VerificationCode::class.java))

            verify()
        }
    }

    private fun verificationCallback () {
        mCallback = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
               error.text = p0.toString()
                error.visibility = View.VISIBLE
            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(p0, p1)
            }
        }
    }
    private fun verify() {

        verificationCallback()

        val newUserPhoneNumber = "+229" + phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                newUserPhoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallback
        )


    }
}
