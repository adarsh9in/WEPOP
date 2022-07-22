package com.example.vepop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.view.*

class SignupActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        tvCanLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        mbSignup.setOnClickListener {
            registeruser()
        }
    }
    private fun validateRegisterDetails():Boolean{
        return when{
            TextUtils.isEmpty(etFirstName.text.toString().trim{it<=' '})->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
            false
        }
        TextUtils.isEmpty(etLastName.text.toString().trim{it<=' '})->{
            showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
            false
    }
            TextUtils.isEmpty(etEnterEmail.text.toString().trim{it<=' '})-> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(etConfirmPassword.text.toString().trim{it<=' '})-> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_confirm_password),
                    true
                )
                false
            }
            etEnterPassword.text.toString().trim { it <= ' ' } != etConfirmPassword.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_password_dont_match),
                    true
                )
                false

            }
            !checkBox.isChecked -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_agree_terms_and_conditions),
                    true
                )
                false

            }
            else -> {
//                showErrorSnackBar("Your details are valid", false)
                true

            }
        }
    }
    private fun registeruser(){
        if (validateRegisterDetails()){
            val email : String =etEnterEmail.text.toString().trim{it<=' '}
            val password : String =etConfirmPassword.text.toString().trim{it<=' '}

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(
                    OnCompleteListener <AuthResult> {task->
                        if (task.isSuccessful) {

                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!


                            //FirestoreClass().registerUser(this@SignupActivity, user)


                           // FirebaseAuth.getInstance().signOut()
                           // finish()
                        }else{
                            showErrorSnackBar(task.exception!!.message.toString(),true)
                        }
                    })
                        }
                    }
        fun userRegistrationSuccess(){
             // todo Hide the progress dialog


            Toast.makeText(
                this@SignupActivity,
                resources.getString(R.string.register_success),
                Toast.LENGTH_SHORT
            ).show()


        }

        }

