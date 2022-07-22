package com.example.vepop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        button_submit.setOnClickListener {
            val email: String = etForgotEmailAddress.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            } else {

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                resources.getString(R.string.email_sent_success),
                                Toast.LENGTH_LONG
                            ).show()

                            finish()
                        }
                        else{
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
            }
        }
    }
}
