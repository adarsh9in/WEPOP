package com.example.vepop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.example.vepop.utils.Constants

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*


class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        //click event assigned to login button
        mbLogin.setOnClickListener(this)

        //click event assigned to signup text
        tvSignup.setOnClickListener(this)

        tvForgotPassword.setOnClickListener(this)


    }
    /*fun userLoggedInSuccess(user: User){
        //todo hide the progress dialog


        if ( user.profileCompleted == 0 ) {
If the user profile is incomplete then launch the UserProfileActivity .
            val intent = Intent (  this@LoginActivity , UserProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS,user)
            startActivity( intent )
        } else {
// Redirect the user to Main Screen after log in .
            startActivity ( Intent (this@LoginActivity , MainActivity :: class.java ) )
        }
        finish ( )

    }*/

    //onClick for Login Signup and Forgot password

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {

                R.id.mbLogin -> {
                    loginRegisteredUser()


                }

                R.id.tvSignup -> {
                    //launch signup screen
                    val intent = Intent(this, SignupActivity::class.java)
                    startActivity(intent)

                }
                R.id.tvForgotPassword->{
                    val intent = Intent(this, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(etEmail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(etPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false


            }
            else -> {
//                showErrorSnackBar("Your details are valid", false)
                true

            }
        }
    }

    private fun loginRegisteredUser() {
        //check with validate function if the entries are valid or not
        if (validateLoginDetails()) {
            val email: String = etEmail.text.toString().trim { it <= ' ' }
            val password: String = etPassword.text.toString().trim { it <= ' ' }

            //Log-In the User using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        //FirestoreClass().getUserDetails(this@LoginActivity)
                    } else {
                        showErrorSnackBar(task.exception!!.message.toString(), true )

                    }

                }

        }
        startActivity(Intent(this,UserProfileActivity::class.java))
        finish()
    }
}