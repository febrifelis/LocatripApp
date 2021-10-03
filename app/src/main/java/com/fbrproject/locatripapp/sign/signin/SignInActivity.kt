package com.fbrproject.locatripapp.sign.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fbrproject.locatripapp.home.HomeActivity
import com.fbrproject.locatrip.R
import com.fbrproject.locatrip.sign.signin.User
import com.fbrproject.locatripapp.sign.signup.SignUpActivity
import com.fbrproject.locatrip.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    lateinit var iUsername: String
    lateinit var iPassword: String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)

        preferences.setValues("onboarding", "1")
        if (preferences.getValues("status").equals("1")) {
            finishAffinity()

            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(intent)
        }
        btn_home.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")) {
                et_username.error = "Silahkan masukkan username anda"
                et_username.requestFocus()
            } else if (iPassword.equals("")) {
                et_password.error = "Silahkan masukkan password anda"
                et_password.requestFocus()
            } else {
                var statusUsername = iUsername.indexOf(".")
                if (statusUsername >= 0) {
                    et_username.error = "Silahkan masukan username anda tanpa titik"
                    et_username.requestFocus()
                } else {
                    pushLogin(iUsername, iPassword)
                }
            }
        }

        btn_daftar.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(
                        this@SignInActivity, "User Tidak Terdaftar",
                        Toast.LENGTH_LONG
                    ).show()
                } else {

                    if (user.password.equals(iPassword)) {

                        preferences.setValues("nama", user.nama.toString())
                        preferences.setValues("user", user.username.toString())
                        preferences.setValues("url", user.url.toString())
                        preferences.setValues("email", user.email.toString())
                        preferences.setLongValues("saldo", user.saldo ?: 0L)
                        preferences.setValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignInActivity, "Password anda salah",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        })
    }
}